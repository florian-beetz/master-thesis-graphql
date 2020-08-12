package de.florianbeetz.ma.graphql.order;

import java.util.List;
import java.util.Map;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import de.florianbeetz.ma.graphql.order.api.InventoryService;
import de.florianbeetz.ma.graphql.order.api.Order;
import de.florianbeetz.ma.graphql.order.api.OrderService;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLSchema customSchema(SchemaParser schemaParser, InventoryService inventoryService,
                                      OrderService orderService) {
        return Federation.transform(schemaParser.makeExecutableSchema())
                         .fetchEntities(env -> env.<List<Map<String, Object>>>getArgument(_Entity.argumentName)
                                 .stream()
                                 .map(values -> {
                                     if ("Order".equals(values.get("__typename"))) {
                                         final String id = values.get("id").toString();
                                         try {
                                             return orderService.lookupOrder(Long.parseLong(id));
                                         } catch (NumberFormatException e) {
                                             return null;
                                         }
                                     }
                                     return null;
                                 })
                         )
                         .resolveEntityType(env -> {
                             final Object src = env.getObject();
                             if (src instanceof Order) {
                                 return env.getSchema().getObjectType("Order");
                             }
                             return null;
                         })
                         .build();
    }
}
