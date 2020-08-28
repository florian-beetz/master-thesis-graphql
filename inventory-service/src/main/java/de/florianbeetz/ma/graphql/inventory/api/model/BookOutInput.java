package de.florianbeetz.ma.graphql.inventory.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookOutInput {

    private final List<StockPosition> positions;

    @JsonCreator
    public BookOutInput(@JsonProperty("positions") List<StockPosition> positions) {
        this.positions = positions;
    }

}
