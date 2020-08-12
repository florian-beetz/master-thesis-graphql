package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;
import java.util.function.BiFunction;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Item {

    private final BiFunction<Integer, Integer, List<ItemStock>> itemStockSupplier;

    @Getter
    private final long id;
    @Getter
    private final String title;
    @Getter
    private final String description;
    @Getter
    private final double price;

    public Item(long id, String title, String description, double price, BiFunction<Integer, Integer, List<ItemStock>> itemStockSupplier) {
        this.itemStockSupplier = itemStockSupplier;
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public List<ItemStock> getStock(Integer page, Integer size) {
        log.debug("Fetching stock of item {} (page={}; size={})", id, page, size);
        return itemStockSupplier.apply(page, size);
    }
}
