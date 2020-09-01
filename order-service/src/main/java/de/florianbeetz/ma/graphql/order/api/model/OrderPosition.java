package de.florianbeetz.ma.graphql.order.api.model;

import java.util.List;

import lombok.Getter;

public class OrderPosition {

    @Getter
    private final Item item;
    @Getter
    private final List<ItemStockPosition> stock;

    public OrderPosition(Item item, List<ItemStockPosition> stock) {
        this.item = item;
        this.stock = stock;
    }

    public long getAmount() {
        return stock.stream()
                .mapToLong(ItemStockPosition::getAmount)
                .sum();
    }
}
