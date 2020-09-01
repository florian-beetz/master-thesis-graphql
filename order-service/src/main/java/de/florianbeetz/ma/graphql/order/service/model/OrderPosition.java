package de.florianbeetz.ma.graphql.order.service.model;

import lombok.Data;

@Data
public class OrderPosition {

    private final long itemId;
    private final long amount;

}
