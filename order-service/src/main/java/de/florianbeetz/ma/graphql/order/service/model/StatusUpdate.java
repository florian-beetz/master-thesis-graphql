package de.florianbeetz.ma.graphql.order.service.model;

import de.florianbeetz.ma.graphql.order.api.model.Order;
import lombok.Data;

@Data
public class StatusUpdate {

    private final OrderStatus previousStatus;
    private final OrderStatus newStatus;
    private final Order order;

}
