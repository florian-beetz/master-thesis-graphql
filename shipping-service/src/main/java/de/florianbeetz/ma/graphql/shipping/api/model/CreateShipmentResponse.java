package de.florianbeetz.ma.graphql.shipping.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Florian Beetz
 */
@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateShipmentResponse implements ApiResponse {

    private final Shipment shipment;

    private final int code;
    private final boolean success;
    private final String message;

    public static CreateShipmentResponse successful(Shipment shipment) {
        return new CreateShipmentResponse(shipment, 0, true, null);
    }

    public static CreateShipmentResponse failure(int code, String message) {
        return new CreateShipmentResponse(null, code, false, message);
    }
}
