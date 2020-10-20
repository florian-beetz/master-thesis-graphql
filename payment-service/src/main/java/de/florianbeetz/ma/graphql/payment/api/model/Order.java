package de.florianbeetz.ma.graphql.payment.api.model;

import lombok.Getter;

/**
 * @author Florian Beetz
 */
public class Order {

    @Getter
    private final long id;

    public Order(long id) {
        this.id = id;
    }
}
