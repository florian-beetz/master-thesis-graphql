package de.florianbeetz.ma.graphql.order.api;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public Order lookupOrder(long id) {
        return new Order(id, List.of(
                new OrderPosition(2, new Item(1), List.of(
                        new ItemStockPosition(3, 1, new ItemStock(3))
                ))
        ));
    }
}
