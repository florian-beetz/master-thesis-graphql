package de.florianbeetz.ma.graphql.shipping.api;

import de.florianbeetz.ma.graphql.shipping.api.model.Shipment;
import de.florianbeetz.ma.graphql.shipping.service.ShippingService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Florian Beetz
 */
@Service
public class Query implements GraphQLQueryResolver {

    private final ShippingService shippingService;

    @Autowired
    public Query(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public Shipment shipment(long id) {
        return shippingService.lookupShipment(id);
    }

}
