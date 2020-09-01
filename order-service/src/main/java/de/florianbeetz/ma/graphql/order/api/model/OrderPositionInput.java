package de.florianbeetz.ma.graphql.order.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

@Data
public class OrderPositionInput {

    private final long itemId;
    private final long amount;

    @JsonCreator
    public OrderPositionInput(@JsonProperty("itemId") long itemId,
                              @JsonProperty("amount") long amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
}
