package de.florianbeetz.ma.graphql.shipping.service;

import java.util.List;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.shipping.api.model.Address;
import de.florianbeetz.ma.graphql.shipping.api.model.Order;
import de.florianbeetz.ma.graphql.shipping.api.model.Shipment;
import de.florianbeetz.ma.graphql.shipping.data.ShipmentEntity;
import de.florianbeetz.ma.graphql.shipping.data.ShipmentRepository;
import de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus;
import de.florianbeetz.ma.graphql.shipping.service.model.StatusUpdate;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Service
public class ShippingService {

    private static final double DEFAULT_SHIPPING_COST = 4.99;

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShippingService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment lookupShipment(long id) {
        return shipmentRepository.findById(id)
                .map(this::fromEntity)
                .orElse(null);
    }

    public Shipment createShipment(Address destinationAddress, long orderId) throws ServiceException {
        if (destinationAddress == null) {
            throw new ServiceException(10, "Parameter 'destinationAddress' is required.");
        }
        if (orderId <= 0) {
            throw new ServiceException(11, "Parameter 'orderId' is not a valid ID.");
        }

        ShipmentEntity entity = new ShipmentEntity(null, orderId, destinationAddress.getStreet(), destinationAddress.getCity(), destinationAddress.getZip(), ShippingStatus.CREATED.name(), false);
        val savedEntity = shipmentRepository.save(entity);

        return fromEntity(savedEntity);
    }

    public StatusUpdate updateShipmentStatus(long id, ShippingStatus status) throws ServiceException {
        val entity = shipmentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(1, "Shipment does not exist."));

        val previousStatus = ShippingStatus.from(entity.getStatus());
        if (!ShippingStatus.isValidTransition(previousStatus, status)) {
            throw new ServiceException(2, "Status transition is not allowed.");
        }

        entity.setStatus(status.name());
        val savedEntity = shipmentRepository.save(entity);

        return new StatusUpdate(status, previousStatus, fromEntity(savedEntity));
    }

    public List<Shipment> getUpdatableShipments() {
        return shipmentRepository.findAllByStatusAndOrderUpdatedFalse(ShippingStatus.READY_TO_SHIP.name()).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public double calculateShippingCost(long shipmentId, long orderId) {
        return DEFAULT_SHIPPING_COST;
    }

    private Shipment fromEntity(ShipmentEntity entity) {
        return new Shipment(
                entity.getId(),
                new Address(
                        entity.getDestinationStreet(),
                        entity.getDestinationCity(),
                        entity.getDestinationZip()
                ),
                new Order(entity.getOrderId()),
                de.florianbeetz.ma.graphql.shipping.api.model.ShippingStatus.from(entity.getStatus()),
                () -> calculateShippingCost(entity.getId(), entity.getOrderId())
        );
    }
}
