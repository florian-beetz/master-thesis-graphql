package de.florianbeetz.ma.graphql.inventory.api.model;

import java.util.List;
import java.util.function.BiFunction;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Warehouse {

    private final BiFunction<Integer, Integer, List<ItemStock>> itemStockSupplier;

    @Getter
    private final long id;
    @Getter
    private final String name;

    public Warehouse(long id, String name, BiFunction<Integer, Integer, List<ItemStock>> itemStockSupplier) {
        this.id = id;
        this.name = name;
        this.itemStockSupplier = itemStockSupplier;
    }

    public List<ItemStock> getStock(Integer page, Integer size) {
        log.debug("Fetching stock of warehouse {} (page={}; size={})", id, page, size);
        return itemStockSupplier.apply(page, size);
    }
}
