package de.florianbeetz.ma.graphql.order.service;

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

}
