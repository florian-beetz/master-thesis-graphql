package de.florianbeetz.ma.graphql.inventory.api;

import java.util.List;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.florianbeetz.ma.graphql.inventory.data.ItemEntity;
import de.florianbeetz.ma.graphql.inventory.data.ItemRepository;
import de.florianbeetz.ma.graphql.inventory.data.ItemStockEntity;
import de.florianbeetz.ma.graphql.inventory.data.ItemStockRepository;
import de.florianbeetz.ma.graphql.inventory.data.WarehouseEntity;
import de.florianbeetz.ma.graphql.inventory.data.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@WithMockUser(roles = "inventory_admin")
class ApiTest extends GraphQLTestTemplate {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemStockRepository itemStockRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @BeforeEach
    void populateDatabase() {
        warehouseRepository.saveAll(List.of(
                new WarehouseEntity(null, "Warehouse 1"),
                new WarehouseEntity(null, "Warehouse 2"),
                new WarehouseEntity(null, "Warehouse 3")
        ));
        itemRepository.saveAll(List.of(
                new ItemEntity(null, "Item 1", "description 1", 1, 0.1),
                new ItemEntity(null, "Item 2", null, 2, 0.2),
                new ItemEntity(null, "Item 3", "description 3", 3, 0.3),
                new ItemEntity(null, "Item 4", null, 4, 0.4)
        ));
        itemStockRepository.saveAll(List.of(
                new ItemStockEntity(null, itemRepository.findById(4L).get(), warehouseRepository.findById(1L).get(), 100, 100),
                new ItemStockEntity(null, itemRepository.findById(6L).get(), warehouseRepository.findById(1L).get(), 100, 0),
                new ItemStockEntity(null, itemRepository.findById(7L).get(), warehouseRepository.findById(1L).get(), 50, 50),
                new ItemStockEntity(null, itemRepository.findById(7L).get(), warehouseRepository.findById(2L).get(), 50, 50)
        ));
    }

    @Test
    void queryExistingItemShouldReturnIt() {
        GraphQLRequest request = GraphQLRequest.query()
                                               .field("item", items -> items
                                                       .param("id", 4)
                                                       .scalar("id")
                                                       .scalar("title")
                                                       .scalar("description")
                                                       .scalar("price")
                                               );

        GraphQLResponse response = postMultipart(request.toString(), "{}");

        assertEquals("4", response.get("data.item.id"));
        assertEquals("Item 1", response.get("data.item.title"));
        assertEquals("description 1", response.get("data.item.description"));
        assertEquals(1, response.get("data.item.price", Double.class));
    }

    @Test
    void queryNonExistingItemShouldReturnNull() {
        GraphQLRequest request = GraphQLRequest.query()
                                               .field("item", items -> items
                                                       .param("id", 42)
                                                       .scalar("id")
                                                       .scalar("title")
                                                       .scalar("description")
                                                       .scalar("price")
                                               );

        GraphQLResponse response = postMultipart(request.toString(), "{}");

        assertNull(response.get("data.item"));
    }

//    @Test
//    void queryExistingWarehouseShouldReturnIt() {
//        GraphQLRequest request = GraphQLRequest.query()
//                                               .field("warehouse", warehouse -> warehouse
//                                                       .param("id", 1)
//                                                       .scalar("id")
//                                                       .scalar("name")
//                                               );
//
//        GraphQLResponse response = postMultipart(request.toString(), "{}");
//
//        assertEquals("1", response.get("data.warehouse.id"));
//        assertEquals("Warehouse 1", response.get("data.warehouse.name"));
//    }

    @Test
    void queryNonExistingWarehouseShouldReturnNull() {
        GraphQLRequest request = GraphQLRequest.query()
                                               .field("warehouse", warehouse -> warehouse
                                                       .param("id", 42)
                                                       .scalar("id")
                                                       .scalar("name")
                                               );

        GraphQLResponse response = postMultipart(request.toString(), "{}");

        assertNull(response.get("data.warehouse"));
    }

}
