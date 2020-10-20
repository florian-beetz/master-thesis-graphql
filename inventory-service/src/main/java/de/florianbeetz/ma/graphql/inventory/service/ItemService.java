package de.florianbeetz.ma.graphql.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import de.florianbeetz.ma.graphql.inventory.api.model.Item;
import de.florianbeetz.ma.graphql.inventory.data.ItemEntity;
import de.florianbeetz.ma.graphql.inventory.data.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Service for CRUD operations on {@link Item Items}.
 */
@Service
public class ItemService {

    /** default page to use if no specific page was requested */
    public static final int DEFAULT_PAGE = 0;
    /** default page size to use if no specific page size was requested */
    public static final int DEFAULT_SIZE = 20;
    /** maximum page size */
    public static final int MAX_SIZE = 200;

    private final ItemRepository itemRepository;

    private ItemStockService itemStockService;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * @param   title       title of the new item
     * @param   description description of the item.
     * @param   price       price of the item.
     *
     * @return  a newly created item.
     */
    public Item createItem(String title, String description, double price) {
        ItemEntity entity = new ItemEntity(null, title, description, price);
        return fromEntity(itemRepository.save(entity));
    }

    /**
     * @param   id  id of the item.
     * @return  the item with the specified id or {@code null} if it does not exist.
     */
    public Item lookupItem(long id) {
        return itemRepository.findById(id).map(this::fromEntity).orElse(null);
    }

    /**
     * @param   page    number of the page
     * @param   size    size of the page, maximally {@link #MAX_SIZE}
     * @return  the items of the given page.
     */
    public List<Item> lookupItems(Integer page, Integer size) {
        return itemRepository.findAll(getPageRequest(page, size))
                             .stream()
                             .map(this::fromEntity)
                             .collect(Collectors.toList());
    }

    /**
     * @param   ids the set of IDs to query.
     * @return  the items with the given IDs.
     */
    public List<Item> lookupItems(List<Long> ids) {
        return StreamSupport.stream(itemRepository.findAllById(ids).spliterator(), false)
                            .map(this::fromEntity)
                            .collect(Collectors.toList());
    }

    Item fromEntity(ItemEntity entity) {
        return new Item(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                (page, size) -> itemStockService.lookupItemStockOfItem(entity.getId(), page, size),
                () -> itemStockService.lookupTotalAvailable(entity.getId()),
                () -> itemStockService.lookupTotalInStock(entity.getId())
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
