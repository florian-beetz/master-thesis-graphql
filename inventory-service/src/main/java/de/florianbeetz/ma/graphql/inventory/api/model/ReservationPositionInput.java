package de.florianbeetz.ma.graphql.inventory.api.model;

import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class ReservationPositionInput {

    private final long stockId;
    private final long amount;

}
