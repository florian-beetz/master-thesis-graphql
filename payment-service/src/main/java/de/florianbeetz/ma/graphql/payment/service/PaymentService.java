package de.florianbeetz.ma.graphql.payment.service;

import java.util.UUID;

import de.florianbeetz.ma.graphql.payment.api.model.Order;
import de.florianbeetz.ma.graphql.payment.api.model.Payment;
import de.florianbeetz.ma.graphql.payment.api.model.PaymentStatus;
import de.florianbeetz.ma.graphql.payment.data.PaymentEntity;
import de.florianbeetz.ma.graphql.payment.data.PaymentRepository;
import de.florianbeetz.ma.graphql.payment.service.model.StatusUpdate;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment lookupPayment(long id) {
        return paymentRepository.findById(id)
                                .map(this::fromEntity)
                                .orElse(null);
    }

    public Payment createPayment(double amount, long orderId) {
        PaymentEntity entity = new PaymentEntity(null, amount, orderId, UUID.randomUUID().toString(), PaymentStatus.CREATED.name(), false);
        return fromEntity(paymentRepository.save(entity));
    }

    public StatusUpdate updateStatus(long id, de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus status) throws ServiceException {
        val paymentEntity = paymentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(1, "Payment does not exist."));

        val previousStatus = de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus.from(paymentEntity.getStatus());
        if (!de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus.isValidTransition(previousStatus, status)) {
            throw new ServiceException(2, "Status transition is not allowed.");
        }

        paymentEntity.setStatus(status.name());
        val savedEntity = paymentRepository.save(paymentEntity);

        return new StatusUpdate(previousStatus, status, fromEntity(savedEntity));
    }

    private Payment fromEntity(PaymentEntity paymentEntity) {
        return new Payment(paymentEntity.getId(),
                paymentEntity.getAmount(),
                new Order(paymentEntity.getOrderId()),
                paymentEntity.getPaymentReference(),
                PaymentStatus.from(paymentEntity.getStatus()));
    }
}
