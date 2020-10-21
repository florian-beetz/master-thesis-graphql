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
                shopApiService.updateOrderStatus(payment.getOrder().getId(), OrderStatus.PAYMENT_RECEIVED);

                updated++;
            } catch (Exception e) {
                log.error("Failed to update order id={} to status payment received", payment.getOrder().getId(), e);
            }
        }

        log.info("Updated {} orders.", updated);
    }

}
