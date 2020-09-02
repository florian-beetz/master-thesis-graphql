package de.florianbeetz.ma.graphql.order.api.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class Order {

    private final long id;
    private final List<OrderPosition> positions;
    private final OrderStatus status;

}
