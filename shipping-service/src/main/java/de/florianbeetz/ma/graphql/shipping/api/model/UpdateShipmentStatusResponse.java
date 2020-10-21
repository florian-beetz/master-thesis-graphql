package de.florianbeetz.ma.graphql.shipping.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Florian Beetz
 */
@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateShipmentStatusResponse implements ApiResponse {

    private final ShippingStatus newStatus;
    private final ShippingStatus previousStatus;
    private final Shipment shipment;

    private final int code;
    private final boolean success;
    private final String message;

    public static UpdateShipmentStatusResponse successful(ShippingStatus newStatus, ShippingStatus previousStatus, Shipment shipment) {
        return new UpdateShipmentStatusResponse(newStatus, previousStatus, shipment, 0, true, null);
    }

    public static UpdateShipmentStatusResponse failure(int code, String message) {
        return new UpdateShipmentStatusResponse(null, null, null, code, false, message);
    }
}
