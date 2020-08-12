package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Query implements GraphQLQueryResolver {

    private final ItemService itemService;
    private final WarehouseService warehouseService;

    @Autowired
    public Query(ItemService itemService, WarehouseService warehouseService) {
        this.itemService = itemService;
        this.warehouseService = warehouseService;
    }

    public Item item(Long id, final DataFetchingEnvironment dataFetchingEnvironment) {
        return itemService.lookupItem(id);
    }

    public List<Item> items(Integer page, Integer size, final DataFetchingEnvironment dataFetchingEnvironment) {
        return itemService.lookupItems(page, size);
    }

    public Warehouse warehouse(Long id, DataFetchingEnvironment dataFetchingEnvironment) {
        return warehouseService.lookupWarehouse(id);
    }

    public List<Warehouse> warehouses(Integer page, Integer size, final DataFetchingEnvironment dataFetchingEnvironment) {
        return warehouseService.lookupWarehouses(page, size);
    }

}
