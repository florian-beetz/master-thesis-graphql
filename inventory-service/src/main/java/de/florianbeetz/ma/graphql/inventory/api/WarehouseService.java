package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.inventory.data.WarehouseEntity;
import de.florianbeetz.ma.graphql.inventory.data.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 20;
    public static final int MAX_SIZE = 200;

    private final WarehouseRepository warehouseRepository;

    private ItemStockService itemStockService;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> lookupWarehouses(int page, int size) {
        return warehouseRepository.findAll(getPageRequest(page, size))
                                  .stream()
                                  .map(this::fromEntity)
                                  .collect(Collectors.toList());
    }

    public Warehouse lookupWarehouse(long id) {
        return warehouseRepository.findById(id).map(this::fromEntity).orElse(null);
    }

    public Warehouse createWarehouse(String name) {
        WarehouseEntity entity = new WarehouseEntity(null, name);
        return fromEntity(warehouseRepository.save(entity));
    }

    Warehouse fromEntity(WarehouseEntity entity) {
        return new Warehouse(
                entity.getId(),
                entity.getName(),
                (page, size) -> itemStockService.lookupItemStockOfWarehouse(entity.getId(), page, size)
        );
    }

    @Autowired
    public void setItemStockService(ItemStockService itemStockService) {
        this.itemStockService = itemStockService;
    }

    private PageRequest getPageRequest(Integer page, Integer size) {
        return PageRequest.of(
                Optional.ofNullable(page).orElse(DEFAULT_PAGE),
                Optional.ofNullable(size).filter(s -> s <= MAX_SIZE).orElse(DEFAULT_SIZE)
        );
    }
}
