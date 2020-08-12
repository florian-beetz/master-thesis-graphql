package de.florianbeetz.ma.graphql.order.api;

import lombok.Getter;

public class Item {

    @Getter
    private final long id;

    public Item(long id) {
        this.id = id;
    }
}
