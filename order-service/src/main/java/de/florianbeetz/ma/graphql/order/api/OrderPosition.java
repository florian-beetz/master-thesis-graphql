package de.florianbeetz.ma.graphql.order.api;

import java.util.List;

import lombok.Getter;

public class OrderPosition {

    @Getter
    private final long id;
    @Getter
    private final Item item;
    @Getter
    private final List<ItemStockPosition> stock;

    public OrderPosition(long id, Item item, List<ItemStockPosition> stock) {
        this.id = id;
        this.item = item;
        this.stock = stock;
    }

    public long getAmount() {
        return stock.stream()
                .mapToInt(ItemStockPosition::getAmount)
                .sum();
    }
}
