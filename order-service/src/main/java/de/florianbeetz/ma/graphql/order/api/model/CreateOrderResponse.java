package de.florianbeetz.ma.graphql.order.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOrderResponse implements ApiResponse {

    private final Order order;
    private final int code;
    private final boolean success;
    private final String message;

    public static CreateOrderResponse successful(Order order) {
        return new CreateOrderResponse(order, 0, true, null);
    }

    public static CreateOrderResponse failure(int code, String message) {
        return new CreateOrderResponse(null, code, false, message);
    }
}
