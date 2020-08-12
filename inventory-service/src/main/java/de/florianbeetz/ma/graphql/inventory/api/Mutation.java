package de.florianbeetz.ma.graphql.inventory.api;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mutation implements GraphQLMutationResolver {

    private final ItemService itemService;
    private final WarehouseService warehouseService;
    private final ItemStockService itemStockService;

    @Autowired
    public Mutation(ItemService itemService, WarehouseService warehouseService, ItemStockService itemStockService) {
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.itemStockService = itemStockService;
    }

    public Item createItem(String title, String description, double price) {
        return itemService.createItem(title, description, price);
    }

    public Warehouse createWarehouse(String name) {
        return warehouseService.createWarehouse(name);
    }

    public ItemStock createItemStock(long itemId, long warehouseId, long inStock, long available) {
        return itemStockService.createItemStock(itemId, warehouseId, inStock, available);
    }

}
