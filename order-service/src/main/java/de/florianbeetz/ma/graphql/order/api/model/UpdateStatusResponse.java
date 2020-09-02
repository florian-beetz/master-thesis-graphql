package de.florianbeetz.ma.graphql.order.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateStatusResponse implements ApiResponse {

    private final OrderStatus previousStatus;
    private final OrderStatus newStatus;
    private final Order order;

    private final int code;
    private final boolean success;
    private final String message;

    public static UpdateStatusResponse successful(OrderStatus previousStatus, OrderStatus newStatus, Order order) {
        return new UpdateStatusResponse(previousStatus, newStatus, order, 0, true, null);
    }

    public static UpdateStatusResponse failure(int code, String message) {
        return new UpdateStatusResponse(null, null, null, code, false, message);
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
