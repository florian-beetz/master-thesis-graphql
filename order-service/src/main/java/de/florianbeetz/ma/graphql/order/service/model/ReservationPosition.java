package de.florianbeetz.ma.graphql.order.service.model;

import lombok.Data;

@Data
public class ReservationPosition {

    private final long itemId;
    private final long itemStockId;
    private final long amount;

}
