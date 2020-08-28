package de.florianbeetz.ma.graphql.inventory.api.model;

import java.util.function.Supplier;

import lombok.Getter;

public class ItemStock {

    private final Supplier<Item> itemSupplier;
    private final Supplier<Warehouse> warehouseSupplier;

    @Getter
    private final long id;
    private Item item = null;
    private Warehouse warehouse = null;
    @Getter
    private final long inStock;
    @Getter
    private final long available;

    public ItemStock(long id, long inStock, long available, Supplier<Item> itemSupplier, Supplier<Warehouse> warehouseSupplier) {
        this.id = id;
        this.inStock = inStock;
        this.available = available;

        this.itemSupplier = itemSupplier;
        this.warehouseSupplier = warehouseSupplier;
    }

    public Item getItem() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    public Warehouse getWarehouse() {
        if (warehouse == null) {
            warehouse = warehouseSupplier.get();
        }
        return warehouse;
    }
}
