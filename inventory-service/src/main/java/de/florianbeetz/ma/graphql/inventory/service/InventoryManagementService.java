package de.florianbeetz.ma.graphql.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.florianbeetz.ma.graphql.inventory.api.model.ItemStock;
import de.florianbeetz.ma.graphql.inventory.data.ItemStockEntity;
import de.florianbeetz.ma.graphql.inventory.data.ItemStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Service to perform inventory tasks aside from CRUD operations.
 */
@Service
public class InventoryManagementService {

    private final ItemStockRepository itemStockRepository;
    private final ItemStockService itemStockService;

    @Autowired
    public InventoryManagementService(ItemStockRepository itemStockRepository, ItemStockService itemStockService) {
        this.itemStockRepository = itemStockRepository;
        this.itemStockService = itemStockService;
    }

    /**
     * Reserves the specified amount of items of the item specified by the item id.
     * The {@link ItemStock stock positions}, that were used to fulfill the reservation are the keys of the retuned
     * {@link Map}. The values are the amount of items taken from this specific position.
     *
     * @param   itemId  id of the item.
     * @param   amount  amount to reserve.
     *
     * @return  the positions of the reservation.
     *
     * @throws  ServiceException
     *          Signals that the reservation can not be fulfilled.
     */
    public Map<ItemStock, Long> reserveItems(long itemId, long amount) throws ServiceException {
        Iterator<ItemStockEntity> page = itemStockRepository.findAllByItemId(itemId, PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "available")))
                                                            .iterator();

        long remainingAmount = amount;
        Map<ItemStock, Long> positions = new HashMap<>();
        List<ItemStockEntity> updatedEntities = new ArrayList<>();
        while (page.hasNext() && remainingAmount > 0) {
            ItemStockEntity stock = page.next();

            long reserveItems = Math.min(stock.getAvailable(), remainingAmount);
            stock.setAvailable(stock.getAvailable() - reserveItems);

            remainingAmount -= reserveItems;
            updatedEntities.add(stock);
            positions.put(itemStockService.fromEntity(stock), reserveItems);
        }

        if (remainingAmount > 0) {
            throw new ServiceException(1, "Not enough items are available to create reservation for " + amount + " items of item " + itemId);
        }

        itemStockRepository.saveAll(updatedEntities);
        return positions;
    }

    /**
     * Books out the given amount of the specified items.
     * The {@code positions} parameter maps the ID of a {@link ItemStock stock position} to the amount to book out.
     * Booking out the items requires that a reservation was made beforehand that equals or exceeds the amount to book
     * out.
     *
     * @param   positions positions to book out.
     *
     * @throws  ServiceException
     *          Signals that the positions could not be booked out. This may happen if no reservation was made
     *          beforehand.
     */
    public void bookOutItems(Map<Long, Long> positions) throws ServiceException {
        Set<ItemStockEntity> updatedEntities = new HashSet<>();
        for (ItemStockEntity entity : itemStockRepository.findAllById(positions.keySet())) {
            bookOutItem(entity, positions.get(entity.getId()));
            updatedEntities.add(entity);
        }

        itemStockRepository.saveAll(updatedEntities);
    }

    /**
     * Releases items for sale in the given amount of the specified item position.
     *
     * @param   positions   positions to cancel.
     *
     * @throws  ServiceException
     *          Signals that the reservations could not be released. This may happen if no reservation was made
     *          beforehand.
     */
    public void cancelReservations(Map<Long, Long> positions) throws ServiceException {
        Set<ItemStockEntity> updatedEntities = new HashSet<>();
        for (ItemStockEntity entity : itemStockRepository.findAllById(positions.keySet())) {
            cancelReservation(entity, positions.get(entity.getId()));
            updatedEntities.add(entity);
        }

        itemStockRepository.saveAll(updatedEntities);
    }

    private void bookOutItem(ItemStockEntity stock, long amount) throws ServiceException {
        if (amount > stock.getReserved()) {
            throw new ServiceException(2, "Amount to book out (" + amount + ") exceeds reserved items (" + stock.getReserved() + ") for stock position " + stock);
        }

        stock.setInStock(stock.getInStock() - amount);
    }

    private void cancelReservation(ItemStockEntity stock, long amount) throws ServiceException {
        if (stock.getReserved() < amount) {
            throw new ServiceException(3, "Amount of reservation to cancel (" + amount + ") exceeds reserved items (" + stock.getReserved() + ") for stock position " + stock);
        }

        stock.setAvailable(stock.getAvailable() + amount);
    }
}
