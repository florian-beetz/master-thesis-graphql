package de.florianbeetz.ma.graphql.inventory.api.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationResponse implements MutationResponse {

    private final List<ReservationPosition> positions;
    private final int code;
    private final boolean success;
    private final String message;

    public static ReservationResponse success(List<ReservationPosition> positions) {
        return new ReservationResponse(positions, 0, true, null);
    }

    public static ReservationResponse failure(int code, String message) {
        return new ReservationResponse(null, code, false, message);
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
