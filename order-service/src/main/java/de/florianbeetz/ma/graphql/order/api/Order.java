package de.florianbeetz.ma.graphql.order.api;

import java.util.List;

import lombok.Getter;

public class Order {

    @Getter
    private final long id;
    @Getter
    private final List<OrderPosition> positions;

    public Order(long id, List<OrderPosition> positions) {
        this.id = id;
        this.positions = positions;
    }
}
