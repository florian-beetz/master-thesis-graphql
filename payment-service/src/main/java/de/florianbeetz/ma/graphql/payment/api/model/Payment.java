package de.florianbeetz.ma.graphql.payment.api.model;

import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class Payment {

    private final long id;
    private final double amount;
    private final Order order;
    private final String reference;
    private final PaymentStatus status;

}
