package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.inventory.data.ItemEntity;
import de.florianbeetz.ma.graphql.inventory.data.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 20;
    public static final int MAX_SIZE = 200;

    private final ItemRepository itemRepository;

    private ItemStockService itemStockService;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(String title, String description, double price) {
        ItemEntity entity = new ItemEntity(null, title, description, price);
        return fromEntity(itemRepository.save(entity));
    }

    public Item lookupItem(long id) {
        return itemRepository.findById(id).map(this::fromEntity).orElse(null);
    }

    public List<Item> lookupItems(Integer page, Integer size) {
        return itemRepository.findAll(getPageRequest(page, size))
                             .stream()
                             .map(this::fromEntity)
                             .collect(Collectors.toList());
    }

    Item fromEntity(ItemEntity entity) {
        return new Item(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                (page, size) -> itemStockService.lookupItemStockOfItem(entity.getId(), page, size)
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
