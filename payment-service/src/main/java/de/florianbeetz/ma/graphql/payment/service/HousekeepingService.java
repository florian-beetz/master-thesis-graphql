package de.florianbeetz.ma.graphql.payment.service;

import de.florianbeetz.ma.graphql.client.OrderStatus;
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

    private final PaymentService paymentService;
    private final ShopApiService shopApiService;

    @Autowired
    public HousekeepingService(PaymentService paymentService, ShopApiService shopApiService) {
        this.paymentService = paymentService;
        this.shopApiService = shopApiService;
    }

    @Scheduled(cron = "${application.housekeeping.order-update}")
    public void updateOrderStatus() {
        log.info("Updating order status...");
        int updated = 0;

        val payments = paymentService.getPayedPaymentsToBeUpdated();
        for (val payment : payments) {
            try {
                val status = shopApiService.getOrderStatus(payment.getOrder().getId());
                if (status == OrderStatus.CREATED) {
                    shopApiService.updateOrderStatus(payment.getOrder().getId(), OrderStatus.PAYMENT_RECEIVED);

                    updated++;
                } else if (status == OrderStatus.PAYMENT_RECEIVED) {
                    log.warn("Did not update order id={} for payment id={}, because it was already in status payment_received", payment.getOrder().getId(), payment.getId());
                } else {
                    log.error("Failed to update order id={} for payment id={}, because it is in status {}", payment.getOrder().getId(), payment.getId(),  status);
                }
            } catch (Exception e) {
                log.error("Failed to update order id={} to status payment received", payment.getOrder().getId(), e);
            }
        }

        log.info("Updated {} orders.", updated);
    }

}
