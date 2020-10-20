package de.florianbeetz.ma.graphql.payment.api;

import de.florianbeetz.ma.graphql.payment.api.model.Payment;
import de.florianbeetz.ma.graphql.payment.service.PaymentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Service
public class Query implements GraphQLQueryResolver {

    private final PaymentService paymentService;

    @Autowired
    public Query(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Payment payment(long id) {
        return paymentService.lookupPayment(id);
    }

}
