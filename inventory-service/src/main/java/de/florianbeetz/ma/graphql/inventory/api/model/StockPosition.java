package de.florianbeetz.ma.graphql.inventory.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockPosition {

    private final long stockId;
    private final long amount;

    @JsonCreator
    public StockPosition(@JsonProperty("stockId") long stockId,
                         @JsonProperty("amount") long amount) {
        this.stockId = stockId;
        this.amount = amount;
    }

}
