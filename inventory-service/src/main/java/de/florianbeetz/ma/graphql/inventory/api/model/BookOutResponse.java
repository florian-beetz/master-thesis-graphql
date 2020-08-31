package de.florianbeetz.ma.graphql.inventory.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BookOutResponse implements ApiResponse {

    private final int code;
    private final boolean success;
    private final String message;

    public static BookOutResponse successful() {
        return new BookOutResponse(0, true, null);
    }

    public static BookOutResponse failure(int code, String message) {
        return new BookOutResponse(code, false, message);
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
