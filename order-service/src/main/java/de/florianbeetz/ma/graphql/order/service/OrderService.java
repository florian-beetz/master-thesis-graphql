package de.florianbeetz.ma.graphql.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.order.api.model.Item;
import de.florianbeetz.ma.graphql.order.api.model.ItemStock;
import de.florianbeetz.ma.graphql.order.api.model.ItemStockPosition;
import de.florianbeetz.ma.graphql.order.api.model.Order;
import de.florianbeetz.ma.graphql.order.api.model.OrderPosition;
import de.florianbeetz.ma.graphql.order.service.model.ReservationPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final InventoryService inventoryService;

    @Autowired
    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public Order lookupOrder(long id) {
        return new Order(id, List.of(
                new OrderPosition(new Item(1), List.of(
                        new ItemStockPosition(1, new ItemStock(3))
                ))
        ));
    }

    public Order createOrder(List<de.florianbeetz.ma.graphql.order.service.model.OrderPosition> positions) throws ServiceException {
        List<ReservationPosition> reservationPositions = inventoryService.reserveItems(positions);
        log.debug("reserved items: {}", reservationPositions);

        List<OrderPosition> orderPositions = reservationPositions.stream()
                                                                 .collect(Collectors.groupingBy(ReservationPosition::getItemId))
                                                                 .entrySet()
                                                                 .stream()
                                                                 .map(e -> new OrderPosition(
                                                                         new Item(e.getKey()),
                                                                         e.getValue().stream()
                                                                          .map(r -> new ItemStockPosition(
                                                                                  r.getAmount(),
                                                                                  new ItemStock(r.getItemStockId())))
                                                                          .collect(Collectors.toList())))
                                                                 .collect(Collectors.toList());

        return new Order(1, orderPositions);
    }
}
