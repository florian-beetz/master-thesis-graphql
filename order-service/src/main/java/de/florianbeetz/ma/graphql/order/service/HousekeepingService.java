package de.florianbeetz.ma.graphql.order.service;

import de.florianbeetz.ma.graphql.client.PaymentStatus;
import de.florianbeetz.ma.graphql.client.ShippingStatus;
import de.florianbeetz.ma.graphql.order.api.model.Order;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Slf4j
@Service
public class HousekeepingService {

    private final OrderService orderService;
    private final ShopApiService shopApiService;

    public HousekeepingService(OrderService orderService, ShopApiService shopApiService) {
        this.orderService = orderService;
        this.shopApiService = shopApiService;
    }

    @Scheduled(cron = "${application.housekeeping.payment-create}")
    public void createPayments() {
        log.info("Creating payments for orders...");
        int created = 0;

        val orders = orderService.getOrdersWithoutPayment();
        for (val order : orders) {
            try {
                val total = orderService.getOrderTotal(order);
                val paymentId = shopApiService.createPayment(total, order.getId());
                orderService.updatePaymentId(order, paymentId);

                created++;
            } catch (Exception e) {
                log.error("Failed to create payment for order id={}", order.getId(), e);
            }
        }

        log.info("Created {} payments.", created);
    }

    @Scheduled(cron = "${application.housekeeping.shipment-create}")
    public void createShipments() {
        log.info("Creating shipments for orders...");
        int created = 0;

        val orders = orderService.getOrdersWithoutShipment();
        for (val order : orders) {
            try {
                val shipmentId = shopApiService.createShipment(order.getDestinationStreet(), order.getDestinationCity(), order.getDestinationZip(), order.getId());
                orderService.updateShipmentId(order, shipmentId);

                created++;
            } catch (Exception e) {
                log.error("Failed to create shipment for order id={}", order.getId(), e);
            }
        }

        log.info("Created {} shipments.", created);
    }

    @Scheduled(cron = "${application.housekeeping.shipment-update}")
    public void updateShipments() {
        log.info("Updating shipments ready to ship...");
        int updated = 0;

        val orders = orderService.getPayedOrders();
        for (val order : orders) {
            try {
                val status = shopApiService.getShipmentStatus(order.getShipment().getId());
                if (status == ShippingStatus.CREATED) {
                    shopApiService.updateShipmentStatus(order.getShipment().getId(), ShippingStatus.READY_TO_SHIP);
                    updated++;
                } else if (status != ShippingStatus.READY_TO_SHIP) {
                    log.error("Can not update status of shipment id={} for order id={}, because it has status {}", order.getShipment().getId(), order.getId(), status);
                }
            } catch (Exception e) {
                log.error("Failed to update shipment id={} for order id={}", order.getShipment().getId(), order.getId(), e);
            }
        }

        log.info("Updated {} shipments", updated);
    }

    @Scheduled(cron = "${application.housekeeping.dangling-subresources}")
    public void cancelShipmentsAndPayments() {
        log.info("Cancelling shipments and payments for cancelled orders...");
        int cancelledShipments = 0;
        int cancelledPayments = 0;

        val orders = orderService.getUpdatableCancelledOrders();
        for (val order : orders) {
            cancelledPayments = cancelPayment(cancelledPayments, order);

            cancelledShipments = cancelShipment(cancelledShipments, order);
        }

        log.info("Cancelled {} shipments and {} payments.", cancelledShipments, cancelledPayments);
    }

    private int cancelShipment(int cancelledShipments, Order order) {
        if (order.getShipment() != null) {
            try {
                val status = shopApiService.getShipmentStatus(order.getShipment().getId());
                if (status == ShippingStatus.CREATED || status == ShippingStatus.READY_TO_SHIP) {
                    shopApiService.updateShipmentStatus(order.getShipment().getId(), ShippingStatus.CANCELLED);
                    orderService.removeShipment(order);
                    cancelledShipments++;
                } else if (status == ShippingStatus.CANCELLED){
                    log.warn("Removing dangling id ({}) of already cancelled shipment from order id={}", order.getShipment().getId(), order.getId());
                    orderService.removeShipment(order);
                } else {
                    log.error("Can not cancel payment id={} for cancelled order id={}, because it has status {}", order.getShipment().getId(), order.getId(), status);
                }
            } catch (Exception e) {
                log.error("Failed to update shipment id={} for order id={}", order.getShipment().getId(), order.getId(), e);
            }
        }
        return cancelledShipments;
    }

    private int cancelPayment(int cancelledPayments, Order order) {
        if (order.getPayment() != null) {
            try {
                val status = shopApiService.getPaymentStatus(order.getPayment().getId());
                if (status == PaymentStatus.CREATED) {
                    shopApiService.updatePaymentStatus(order.getPayment().getId(), PaymentStatus.CANCELLED);
                    orderService.removePayment(order);
                    cancelledPayments++;
                } else if (status == PaymentStatus.CANCELLED) {
                    log.warn("Removing dangling id ({}) of already cancelled payment from order id={}", order.getPayment().getId(), order.getId());
                    orderService.removePayment(order);
                } else {
                    log.error("Can not cancel payment id={} for cancelled order id={}, because it has status {}", order.getPayment().getId(), order.getId(), status);
                }
            } catch (Exception e) {
                log.error("Failed to update payment id={} for order id={}", order.getPayment().getId(), order.getId(), e);
            }
        }
        return cancelledPayments;
    }
}
