package de.florianbeetz.ma.graphql.inventory.api.model;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;

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
    private final Supplier<Long> totalAvailableSupplier;
    private final Supplier<Long> totalInStockSupplier;

    public Item(long id,
                String title,
                String description,
                double price,
                BiFunction<Integer, Integer, List<ItemStock>> itemStockSupplier,
                Supplier<Long> totalAvailableSupplier,
                Supplier<Long> totalInStockSupplier) {
        this.itemStockSupplier = itemStockSupplier;
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.totalAvailableSupplier = totalAvailableSupplier;
        this.totalInStockSupplier = totalInStockSupplier;
    }

    public List<ItemStock> getStock(Integer page, Integer size) {
        log.debug("Fetching stock of item {} (page={}; size={})", id, page, size);
        return itemStockSupplier.apply(page, size);
    }

    public long getTotalAvailable() {
        log.debug("Fetching total available stock of item {}", id);
        return totalAvailableSupplier.get();
    }

    public long getTotalInStock() {
        log.debug("Fetching total stock of item {}", id);
        return totalInStockSupplier.get();
    }
}
