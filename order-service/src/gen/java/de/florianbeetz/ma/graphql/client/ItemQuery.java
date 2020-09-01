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

public class ItemQuery extends Query<ItemQuery> {
    ItemQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public ItemQuery description() {
        startField("description");

        return this;
    }

    public ItemQuery id() {
        startField("id");

        return this;
    }

    public ItemQuery price() {
        startField("price");

        return this;
    }

    public class StockArguments extends Arguments {
        StockArguments(StringBuilder _queryBuilder) {
            super(_queryBuilder, true);
        }

        public StockArguments page(Long value) {
            if (value != null) {
                startArgument("page");
                _queryBuilder.append(value);
            }
            return this;
        }

        public StockArguments size(Long value) {
            if (value != null) {
                startArgument("size");
                _queryBuilder.append(value);
            }
            return this;
        }
    }

    public interface StockArgumentsDefinition {
        void define(StockArguments args);
    }

    public ItemQuery stock(ItemStockQueryDefinition queryDef) {
        return stock(args -> {}, queryDef);
    }

    public ItemQuery stock(StockArgumentsDefinition argsDef, ItemStockQueryDefinition queryDef) {
        startField("stock");

        StockArguments args = new StockArguments(_queryBuilder);
        argsDef.define(args);
        StockArguments.end(args);

        _queryBuilder.append('{');
        queryDef.define(new ItemStockQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public ItemQuery title() {
        startField("title");

        return this;
    }

    public ItemQuery totalAvailable() {
        startField("totalAvailable");

        return this;
    }

    public ItemQuery totalInStock() {
        startField("totalInStock");

        return this;
    }
}
