package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.inventory.api.model.BookOutInput;
import de.florianbeetz.ma.graphql.inventory.api.model.BookOutResponse;
import de.florianbeetz.ma.graphql.inventory.api.model.CancelReservationsResponse;
import de.florianbeetz.ma.graphql.inventory.api.model.Item;
import de.florianbeetz.ma.graphql.inventory.api.model.ItemStock;
import de.florianbeetz.ma.graphql.inventory.api.model.ReservationPosition;
import de.florianbeetz.ma.graphql.inventory.api.model.ReservationPositionInput;
import de.florianbeetz.ma.graphql.inventory.api.model.ReservationResponse;
import de.florianbeetz.ma.graphql.inventory.api.model.StockPosition;
import de.florianbeetz.ma.graphql.inventory.api.model.Warehouse;
import de.florianbeetz.ma.graphql.inventory.service.InventoryManagementService;
import de.florianbeetz.ma.graphql.inventory.service.ItemService;
import de.florianbeetz.ma.graphql.inventory.service.ItemStockService;
import de.florianbeetz.ma.graphql.inventory.service.ServiceException;
import de.florianbeetz.ma.graphql.inventory.service.WarehouseService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Main entrypoint for handling GraphQL mutations.
 */
@Service
public class Mutation implements GraphQLMutationResolver {

    private final ItemService itemService;
    private final WarehouseService warehouseService;
    private final ItemStockService itemStockService;
    private final InventoryManagementService inventoryManagementService;

    @Autowired
    public Mutation(ItemService itemService, WarehouseService warehouseService, ItemStockService itemStockService, InventoryManagementService inventoryManagementService) {
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.itemStockService = itemStockService;
        this.inventoryManagementService = inventoryManagementService;
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public Item createItem(String title, String description, double price) {
        return itemService.createItem(title, description, price);
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public Warehouse createWarehouse(String name) {
        return warehouseService.createWarehouse(name);
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public ItemStock createItemStock(long itemId, long warehouseId, long inStock, long available) {
        return itemStockService.createItemStock(itemId, warehouseId, inStock, available);
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public ReservationResponse reserveItems(long itemId, long amount) {
        Map<ItemStock, Long> positions;
        try {
            positions = inventoryManagementService.reserveItems(itemId, amount);
        } catch (ServiceException e) {
            return ReservationResponse.failure(e.getCode(), e.getMessage());
        }

        return ReservationResponse.success(positions.entrySet().stream()
                                                    .map(e -> new ReservationPosition(e.getKey(), e.getValue()))
                                                    .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public BookOutResponse bookOutItems(BookOutInput bookOutInput) {
        Map<Long, Long> positions = bookOutInput.getPositions().stream()
                                                .collect(Collectors.toMap(
                                                        StockPosition::getStockId,
                                                        StockPosition::getAmount
                                                ));

        try {
            inventoryManagementService.bookOutItems(positions);
        } catch (ServiceException e) {
            return BookOutResponse.failure(e.getCode(), e.getMessage());
        }
        return BookOutResponse.successful();
    }

    @PreAuthorize("hasRole('inventory_admin')")
    public CancelReservationsResponse cancelReservations(List<ReservationPositionInput> positions) {
        Map<Long, Long> pos = positions.stream()
                                       .collect(Collectors.toMap(ReservationPositionInput::getStockId, ReservationPositionInput::getAmount));
        try {
            inventoryManagementService.cancelReservations(pos);
        } catch (ServiceException e) {
            return CancelReservationsResponse.failure(e.getCode(), e.getMessage());
        }
        return CancelReservationsResponse.successful();
    }
}
