package de.florianbeetz.ma.graphql.inventory.api.model;

import lombok.Data;

@Data
public class ReservationPosition {

    private final ItemStock stock;
    private final long amount;

    public Item getItem() {
        return stock.getItem();
    }

}
