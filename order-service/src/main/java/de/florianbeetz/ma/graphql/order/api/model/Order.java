package de.florianbeetz.ma.graphql.order.api.model;

import java.util.List;

import lombok.Data;

@Data
public class Order {

    private final long id;
    private final List<OrderPosition> positions;
    private final OrderStatus status;
    private final Payment payment;
    private final Shipment shipment;

    private final String destinationStreet;
    private final String destinationCity;
    private final String destinationZip;
}
