package de.florianbeetz.ma.graphql.order.api.model;

import lombok.Getter;

public class ItemStockPosition {

    @Getter
    private final long amount;
    @Getter
    private final ItemStock stock;

    public ItemStockPosition(long amount, ItemStock stock) {
        this.amount = amount;
        this.stock = stock;
    }
}
