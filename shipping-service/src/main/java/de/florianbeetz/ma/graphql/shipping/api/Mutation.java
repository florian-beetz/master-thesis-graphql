package de.florianbeetz.ma.graphql.shipping.api;

import de.florianbeetz.ma.graphql.shipping.api.model.AddressInput;
import de.florianbeetz.ma.graphql.shipping.api.model.CreateShipmentResponse;
import de.florianbeetz.ma.graphql.shipping.api.model.Shipment;
import de.florianbeetz.ma.graphql.shipping.api.model.ShippingStatus;
import de.florianbeetz.ma.graphql.shipping.api.model.UpdateShipmentStatusResponse;
import de.florianbeetz.ma.graphql.shipping.service.ServiceException;
import de.florianbeetz.ma.graphql.shipping.service.ShippingService;
import de.florianbeetz.ma.graphql.shipping.service.model.StatusUpdate;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Service
public class Mutation implements GraphQLMutationResolver {

    private final ShippingService shippingService;

    @Autowired
    public Mutation(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public CreateShipmentResponse createShipment(AddressInput destinationAddress, long orderId) {
        try {
            Shipment shipment = shippingService.createShipment(destinationAddress.toAddress(), orderId);
            return CreateShipmentResponse.successful(shipment);
        } catch (ServiceException e) {
            return CreateShipmentResponse.failure(e.getCode(), e.getMessage());
        }
    }

    public UpdateShipmentStatusResponse updateShipmentStatus(long shipmentId, ShippingStatus status) {
        try {
            StatusUpdate statusUpdate = shippingService.updateShipmentStatus(shipmentId, status.toServiceModel());

            return UpdateShipmentStatusResponse.successful(
                    ShippingStatus.from(statusUpdate.getNewStatus()),
                    ShippingStatus.from(statusUpdate.getPreviousStatus()),
                    statusUpdate.getShipment());
        } catch (ServiceException e) {
            return UpdateShipmentStatusResponse.failure(e.getCode(), e.getMessage());
        }
    }

}
