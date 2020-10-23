package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;

import de.florianbeetz.ma.graphql.inventory.api.model.Item;
import de.florianbeetz.ma.graphql.inventory.api.model.Warehouse;
import de.florianbeetz.ma.graphql.inventory.service.ItemService;
import de.florianbeetz.ma.graphql.inventory.service.WarehouseService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Main entry point for handling GraphQL queries.
 */
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

    public List<Item> items(Integer page, Integer size, List<Long> ids, final DataFetchingEnvironment dataFetchingEnvironment) {
        if (ids == null) {
            return itemService.lookupItems(page, size);
        } else {
            return itemService.lookupItems(ids);
        }
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public Warehouse warehouse(Long id, DataFetchingEnvironment dataFetchingEnvironment) {
        return warehouseService.lookupWarehouse(id);
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public List<Warehouse> warehouses(Integer page, Integer size, final DataFetchingEnvironment dataFetchingEnvironment) {
        return warehouseService.lookupWarehouses(page, size);
    }

}
