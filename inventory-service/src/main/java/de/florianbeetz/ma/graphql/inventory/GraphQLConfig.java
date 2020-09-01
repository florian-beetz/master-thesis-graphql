package de.florianbeetz.ma.graphql.inventory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import de.florianbeetz.ma.graphql.inventory.api.model.Item;
import de.florianbeetz.ma.graphql.inventory.service.ItemService;
import de.florianbeetz.ma.graphql.inventory.api.model.ItemStock;
import de.florianbeetz.ma.graphql.inventory.service.ItemStockService;
import de.florianbeetz.ma.graphql.inventory.api.model.Warehouse;
import de.florianbeetz.ma.graphql.inventory.service.WarehouseService;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GraphQLConfig {

    @Data
    private static class GraphQLType<T> {
        private final String name;
        private final Class<T> modelClass;
        private final Function<Long, T> fetcher;
    }

    private final Set<GraphQLType<?>> types;

    @Autowired
    public GraphQLConfig(ItemService itemService, WarehouseService warehouseService, ItemStockService itemStockService) {
        types = Set.of(
                new GraphQLType<>("Item", Item.class, itemService::lookupItem),
                new GraphQLType<>("Warehouse", Warehouse.class, warehouseService::lookupWarehouse),
                new GraphQLType<>("ItemStock", ItemStock.class, itemStockService::lookupItemStock)
        );
    }

    /**
     * Transform GraphQL schema to enable other services to resolve types owned by this service.
     *
     * @param   schemaParser
     *
     * @return  transformed schema.
     */
    @Bean
    public GraphQLSchema customSchema(SchemaParser schemaParser) {
        return Federation.transform(schemaParser.makeExecutableSchema())
                         .fetchEntities(env -> env.<List<Map<String, Object>>>getArgument(_Entity.argumentName)
                                 .stream()
                                 .map(values -> types.stream()
                                                     .filter(t -> t.getName().equals(values.get("__typename")))
                                                     .findAny()
                                                     .map(t -> {
                                                         final String id = values.get("id").toString();
                                                         try {
                                                             Object result = t.getFetcher().apply(Long.parseLong(id));
                                                             log.debug("fetching object of type {} resulted in {} (values='{}')", t.getName(), result.getClass(), values);
                                                             return result;
                                                         } catch (NumberFormatException e) {
                                                             log.debug("failed to fetch entity of type {} because '{}' is not a valid ID", t.getName(), id);
                                                             return null;
                                                         }
                                                     })
                                                     .orElseGet(() -> {
                                                         log.error("Failed to resolve type {}", values.get("__typename"));
                                                         return null;
                                                     }))
                                 .collect(Collectors.toList()))
                         .resolveEntityType(env -> types.stream()
                                                        .filter(t -> t.getModelClass().isInstance(env.getObject()))
                                                        .peek(t -> log.info("resolving type {} to schema type {}", env.getObject().getClass(), t.getName()))
                                                        .findAny()
                                                        .map(t -> env.getSchema().getObjectType(t.getName()))
                                                        .orElse(null))
                         .build();
    }

}
