package de.florianbeetz.ma.graphql.order.service.model;

import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class ItemPrice {

    private final long itemId;
    private final double price;

}
