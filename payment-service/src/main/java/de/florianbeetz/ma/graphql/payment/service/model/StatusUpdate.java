package de.florianbeetz.ma.graphql.payment.service.model;

import de.florianbeetz.ma.graphql.payment.api.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class StatusUpdate {

    private final PaymentStatus previousStatus;
    private final PaymentStatus newStatus;
    private final Payment payment;

}
