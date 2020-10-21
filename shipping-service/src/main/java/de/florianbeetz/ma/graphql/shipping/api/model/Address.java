package de.florianbeetz.ma.graphql.shipping.api.model;

import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class Address {

    private final String street;
    private final String city;
    private final String zip;

}
