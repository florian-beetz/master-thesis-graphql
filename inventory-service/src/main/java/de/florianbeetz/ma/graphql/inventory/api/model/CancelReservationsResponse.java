package de.florianbeetz.ma.graphql.inventory.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelReservationsResponse implements ApiResponse {

    private final int code;
    private final boolean success;
    private final String message;

    public static CancelReservationsResponse successful() {
        return new CancelReservationsResponse(0, true, null);
    }

    public static CancelReservationsResponse failure(int code, String message) {
        return new CancelReservationsResponse(code, false, message);
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
