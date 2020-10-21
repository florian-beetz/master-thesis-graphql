package de.florianbeetz.ma.graphql.shipping.api.model;

import java.util.function.Supplier;

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
    private final Supplier<Double> costSupplier;

    public double getCost() {
        return costSupplier.get();
    }
}
