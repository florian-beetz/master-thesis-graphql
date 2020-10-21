package de.florianbeetz.ma.graphql.shipping.service;

import de.florianbeetz.ma.graphql.client.OrderStatus;
import de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Slf4j
@Service
public class HousekeepingService {

    private final ShippingService shippingService;
    private final ShopApiService shopApiService;

    @Autowired
    public HousekeepingService(ShippingService shippingService, ShopApiService shopApiService) {
        this.shippingService = shippingService;
        this.shopApiService = shopApiService;
    }

    @Scheduled(cron = "${application.housekeeping.order-update}")
    public void updateOrderStatus() {
        log.info("Processing shipments ready-for-shipping...");
        int updated = 0;

        val shipments = shippingService.getUpdatableShipments();
        for (val shipment : shipments) {
            try {
                shippingService.updateShipmentStatus(shipment.getId(), ShippingStatus.SHIPPED);

                val status = shopApiService.getOrderStatus(shipment.getOrder().getId());
                if (status == OrderStatus.PAYMENT_RECEIVED) {
                    shopApiService.updateOrderStatus(shipment.getOrder().getId(), OrderStatus.SHIPPED);
                    updated++;
                } else if (status == OrderStatus.SHIPPED) {
                    log.warn("Shipping id={} was just shipped, but order id={} was already in status shipped", shipment.getId(), shipment.getOrder().getId());
                } else {
                    log.error("Could not update order id={} for shipment id={}, because it is in status {}", shipment.getOrder().getId(), shipment.getId(), status);
                }
            } catch (ServiceException | ApiException e) {
                log.error("Failed to update order for shipment id={}", shipment.getId(), e);
            }
        }

        log.info("Shipped {} shipments.", updated);
    }

}
