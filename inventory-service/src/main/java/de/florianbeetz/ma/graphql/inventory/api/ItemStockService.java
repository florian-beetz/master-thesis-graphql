package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.inventory.data.ItemEntity;
import de.florianbeetz.ma.graphql.inventory.data.ItemRepository;
import de.florianbeetz.ma.graphql.inventory.data.ItemStockEntity;
import de.florianbeetz.ma.graphql.inventory.data.ItemStockRepository;
import de.florianbeetz.ma.graphql.inventory.data.WarehouseEntity;
import de.florianbeetz.ma.graphql.inventory.data.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemStockService {

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 20;
    public static final int MAX_SIZE = 200;

    private final ItemStockRepository itemStockRepository;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;

    private ItemService itemService;
    private WarehouseService warehouseService;

    @Autowired
    public ItemStockService(ItemStockRepository itemStockRepository, ItemRepository itemRepository, WarehouseRepository warehouseRepository) {
        this.itemStockRepository = itemStockRepository;
        this.itemRepository = itemRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public ItemStock lookupItemStock(long id) {
        return itemStockRepository.findById(id).map(this::fromEntity).orElse(null);
    }

    public ItemStock createItemStock(long itemId, long warehouseId, long inStock, long available) {
        ItemEntity item = itemRepository.findById(itemId).get(); // TODO: handle not found
        WarehouseEntity warehouse = warehouseRepository.findById(warehouseId).get();

        ItemStockEntity entity = new ItemStockEntity(null, item, warehouse, inStock, available);
        return fromEntity(itemStockRepository.save(entity));
    }

    public List<ItemStock> lookupItemStockOfItem(long itemId, Integer page, Integer size) {
        return itemStockRepository.findAllByItemId(itemId, getPageRequest(page, size))
                .stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ItemStock> lookupItemStockOfWarehouse(long warehouseId, Integer page, Integer size) {
        return itemStockRepository.findAllByWarehouseId(warehouseId, getPageRequest(page, size))
                .stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    ItemStock fromEntity(ItemStockEntity entity) {
        return new ItemStock(
                entity.getId(),
                entity.getInStock(),
                entity.getAvailable(),
                () -> itemService.fromEntity(entity.getItem()),
                () -> warehouseService.fromEntity(entity.getWarehouse())
        );
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    private PageRequest getPageRequest(Integer page, Integer size) {
        return PageRequest.of(
                Optional.ofNullable(page).orElse(DEFAULT_PAGE),
                Optional.ofNullable(size).filter(s -> s <= MAX_SIZE).orElse(DEFAULT_SIZE)
        );
    }
}
