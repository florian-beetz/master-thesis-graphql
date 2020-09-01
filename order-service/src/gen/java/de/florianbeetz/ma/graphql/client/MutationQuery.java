// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.Arguments;
import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.Query;
import com.shopify.graphql.support.SchemaViolationError;
import com.shopify.graphql.support.TopLevelResponse;
import com.shopify.graphql.support.Input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MutationQuery extends Query<MutationQuery> {
    MutationQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public MutationQuery bookOutItems(BookOutInput bookOutInput, BookOutResponseQueryDefinition queryDef) {
        startField("bookOutItems");

        _queryBuilder.append("(bookOutInput:");
        bookOutInput.appendTo(_queryBuilder);

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new BookOutResponseQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public class CreateItemArguments extends Arguments {
        CreateItemArguments(StringBuilder _queryBuilder) {
            super(_queryBuilder, false);
        }

        public CreateItemArguments description(String value) {
            if (value != null) {
                startArgument("description");
                Query.appendQuotedString(_queryBuilder, value.toString());
            }
            return this;
        }
    }

    public interface CreateItemArgumentsDefinition {
        void define(CreateItemArguments args);
    }

    public MutationQuery createItem(double price, String title, ItemQueryDefinition queryDef) {
        return createItem(price, title, args -> {}, queryDef);
    }

    public MutationQuery createItem(double price, String title, CreateItemArgumentsDefinition argsDef, ItemQueryDefinition queryDef) {
        startField("createItem");

        _queryBuilder.append("(price:");
        _queryBuilder.append(price);

        _queryBuilder.append(",title:");
        Query.appendQuotedString(_queryBuilder, title.toString());

        argsDef.define(new CreateItemArguments(_queryBuilder));

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new ItemQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public class CreateItemStockArguments extends Arguments {
        CreateItemStockArguments(StringBuilder _queryBuilder) {
            super(_queryBuilder, false);
        }

        public CreateItemStockArguments available(Long value) {
            if (value != null) {
                startArgument("available");
                _queryBuilder.append(value);
            }
            return this;
        }
    }

    public interface CreateItemStockArgumentsDefinition {
        void define(CreateItemStockArguments args);
    }

    public MutationQuery createItemStock(Long inStock, Long itemId, Long warehouseId, ItemStockQueryDefinition queryDef) {
        return createItemStock(inStock, itemId, warehouseId, args -> {}, queryDef);
    }

    public MutationQuery createItemStock(Long inStock, Long itemId, Long warehouseId, CreateItemStockArgumentsDefinition argsDef, ItemStockQueryDefinition queryDef) {
        startField("createItemStock");

        _queryBuilder.append("(inStock:");
        _queryBuilder.append(inStock);

        _queryBuilder.append(",itemId:");
        Query.appendQuotedString(_queryBuilder, itemId.toString());

        _queryBuilder.append(",warehouseId:");
        Query.appendQuotedString(_queryBuilder, warehouseId.toString());

        argsDef.define(new CreateItemStockArguments(_queryBuilder));

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new ItemStockQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public MutationQuery createWarehouse(String name, WarehouseQueryDefinition queryDef) {
        startField("createWarehouse");

        _queryBuilder.append("(name:");
        Query.appendQuotedString(_queryBuilder, name.toString());

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new WarehouseQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public MutationQuery reserveItems(Long amount, Long itemId, ReservationResponseQueryDefinition queryDef) {
        startField("reserveItems");

        _queryBuilder.append("(amount:");
        _queryBuilder.append(amount);

        _queryBuilder.append(",itemId:");
        Query.appendQuotedString(_queryBuilder, itemId.toString());

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new ReservationResponseQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public MutationQuery createOrder(List<OrderPositionInput> positions, CreateOrderResponseQueryDefinition queryDef) {
        startField("createOrder");

        _queryBuilder.append("(positions:");
        _queryBuilder.append('[');
        {
            String listSeperator1 = "";
            for (OrderPositionInput item1 : positions) {
                _queryBuilder.append(listSeperator1);
                listSeperator1 = ",";
                item1.appendTo(_queryBuilder);
            }
        }
        _queryBuilder.append(']');

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new CreateOrderResponseQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public String toString() {
        return _queryBuilder.toString();
    }
}
