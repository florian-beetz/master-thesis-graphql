package de.florianbeetz.ma.graphql.order.api;

import lombok.Getter;

public class ItemStockPosition {

    @Getter
    private final long id;
    @Getter
    private final int amount;
    @Getter
    private final ItemStock stock;

    public ItemStockPosition(long id, int amount, ItemStock stock) {
        this.id = id;
        this.amount = amount;
        this.stock = stock;
    }
}
