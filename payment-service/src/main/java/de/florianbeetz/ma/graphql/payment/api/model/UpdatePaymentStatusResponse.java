package de.florianbeetz.ma.graphql.payment.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Florian Beetz
 */
@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePaymentStatusResponse implements ApiResponse {

    private final PaymentStatus newStatus;
    private final PaymentStatus previousStatus;
    private final Payment payment;

    private final int code;
    private final boolean success;
    private final String message;

    public static UpdatePaymentStatusResponse successful(PaymentStatus newStatus, PaymentStatus previousStatus, Payment payment) {
        return new UpdatePaymentStatusResponse(newStatus, previousStatus, payment, 0, true, null);
    }

    public static UpdatePaymentStatusResponse failure(int code, String message) {
        return new UpdatePaymentStatusResponse(null, null, null, code, false, message);
    }

}
