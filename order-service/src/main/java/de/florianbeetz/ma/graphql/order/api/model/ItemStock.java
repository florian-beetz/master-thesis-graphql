package de.florianbeetz.ma.graphql.order.api.model;

import lombok.Getter;

public class ItemStock {

    @Getter
    private final long id;

    public ItemStock(long id) {
        this.id = id;
    }
}
