package de.florianbeetz.ma.graphql.order.api.model;

import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class AddressInput {

    private final String street;
    private final String city;
    private final String zip;

}
