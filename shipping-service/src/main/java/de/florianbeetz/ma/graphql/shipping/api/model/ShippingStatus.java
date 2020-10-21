package de.florianbeetz.ma.graphql.shipping.api.model;

import java.util.stream.Stream;

/**
 * @author Florian Beetz
 */
public enum ShippingStatus {

    CREATED(de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus.CREATED),
    READY_TO_SHIP(de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus.READY_TO_SHIP),
    SHIPPED(de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus.SHIPPED),
    CANCELLED(de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus.CANCELLED)

    ;

    private final de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus serviceModel;

    ShippingStatus(de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus serviceModel) {
        this.serviceModel = serviceModel;
    }

    public de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus toServiceModel() {
        return serviceModel;
    }

    public static ShippingStatus from(String value) {
        return Stream.of(ShippingStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }

    public static ShippingStatus from(de.florianbeetz.ma.graphql.shipping.service.model.ShippingStatus serviceModel) {
        return Stream.of(ShippingStatus.values())
                .filter(status -> status.serviceModel == serviceModel)
                .findAny()
                .orElse(null);
    }

}
