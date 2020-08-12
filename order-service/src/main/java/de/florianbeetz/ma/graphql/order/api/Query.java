package de.florianbeetz.ma.graphql.order.api;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Query implements GraphQLQueryResolver {

    private final OrderService orderService;

    @Autowired
    public Query(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order order(long id, DataFetchingEnvironment env) {
        return orderService.lookupOrder(id);
    }

}
