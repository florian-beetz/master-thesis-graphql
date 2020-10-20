package de.florianbeetz.ma.graphql.payment.api;

import de.florianbeetz.ma.graphql.payment.api.model.CreatePaymentResponse;
import de.florianbeetz.ma.graphql.payment.api.model.Payment;
import de.florianbeetz.ma.graphql.payment.api.model.PaymentStatus;
import de.florianbeetz.ma.graphql.payment.api.model.UpdatePaymentStatusResponse;
import de.florianbeetz.ma.graphql.payment.service.PaymentService;
import de.florianbeetz.ma.graphql.payment.service.ServiceException;
import de.florianbeetz.ma.graphql.payment.service.model.StatusUpdate;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Service
public class Mutation implements GraphQLMutationResolver {

    private final PaymentService paymentService;

    @Autowired
    public Mutation(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public CreatePaymentResponse createPayment(double amount, long orderId) {
        Payment payment = paymentService.createPayment(amount, orderId);

        return CreatePaymentResponse.successful(payment);
    }

    public UpdatePaymentStatusResponse updatePaymentStatus(long paymentId, PaymentStatus status) {
        try {
            StatusUpdate statusUpdate = paymentService.updateStatus(paymentId, status.toServiceModel());
            return UpdatePaymentStatusResponse.successful(PaymentStatus.from(statusUpdate.getNewStatus()),
                    PaymentStatus.from(statusUpdate.getPreviousStatus()),
                    statusUpdate.getPayment());
        } catch (ServiceException e) {
            return UpdatePaymentStatusResponse.failure(e.getCode(), e.getMessage());
        }
    }

}
