package de.florianbeetz.ma.graphql.payment.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Florian Beetz
 */
@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePaymentResponse {

    private final Payment payment;

    private final int code;
    private final boolean success;
    private final String message;

    public static CreatePaymentResponse successful(Payment payment) {
        return new CreatePaymentResponse(payment, 0, true, null);
    }

    public static CreatePaymentResponse failure(int code, String message) {
        return new CreatePaymentResponse(null, code, false, message);
    }

}
