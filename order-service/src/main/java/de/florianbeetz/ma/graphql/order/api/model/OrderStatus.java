package de.florianbeetz.ma.graphql.order.api.model;

import java.util.stream.Stream;

public enum OrderStatus {

    CREATED(de.florianbeetz.ma.graphql.order.service.model.OrderStatus.CREATED),

    PAYMENT_RECEIVED(de.florianbeetz.ma.graphql.order.service.model.OrderStatus.PAYMENT_RECEIVED),

    SHIPPED(de.florianbeetz.ma.graphql.order.service.model.OrderStatus.SHIPPED),

    CANCELED(de.florianbeetz.ma.graphql.order.service.model.OrderStatus.CANCELED);

    private final de.florianbeetz.ma.graphql.order.service.model.OrderStatus serviceModel;

    OrderStatus(de.florianbeetz.ma.graphql.order.service.model.OrderStatus serviceModel) {
        this.serviceModel = serviceModel;
    }

    public de.florianbeetz.ma.graphql.order.service.model.OrderStatus toServiceModel() {
        return serviceModel;
    }

    public static OrderStatus from(de.florianbeetz.ma.graphql.order.service.model.OrderStatus status) {
        return Stream.of(OrderStatus.values())
                .filter(s -> s.toServiceModel() == status)
                .findAny()
                .orElse(null);
    }

    public static OrderStatus from(String value) {
        return Stream.of(OrderStatus.values())
                .filter(s -> value.equalsIgnoreCase(s.name()))
                .findAny()
                .orElse(null);
    }
}
