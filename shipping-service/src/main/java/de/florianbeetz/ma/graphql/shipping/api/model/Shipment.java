package de.florianbeetz.ma.graphql.shipping.api.model;

import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class Shipment {

    private final long id;
    private final Address destinationAddress;
    private final Order order;
    private final ShippingStatus status;

    public double getCost() {
        return 0.0; // TODO: implement cost calculation
    }
}
