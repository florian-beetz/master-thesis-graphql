package de.florianbeetz.ma.graphql.shipping.service.model;

import de.florianbeetz.ma.graphql.shipping.api.model.Shipment;
import lombok.Data;

/**
 * @author Florian Beetz
 */
@Data
public class StatusUpdate {

    private final ShippingStatus newStatus;
    private final ShippingStatus previousStatus;
    private final Shipment shipment;

}
