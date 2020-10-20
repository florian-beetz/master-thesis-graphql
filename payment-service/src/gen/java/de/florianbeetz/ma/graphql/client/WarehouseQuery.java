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

public class WarehouseQuery extends Query<WarehouseQuery> {
    WarehouseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public WarehouseQuery id() {
        startField("id");

        return this;
    }

    public WarehouseQuery name() {
        startField("name");

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

    public WarehouseQuery stock(ItemStockQueryDefinition queryDef) {
        return stock(args -> {}, queryDef);
    }

    public WarehouseQuery stock(StockArgumentsDefinition argsDef, ItemStockQueryDefinition queryDef) {
        startField("stock");

        StockArguments args = new StockArguments(_queryBuilder);
        argsDef.define(args);
        StockArguments.end(args);

        _queryBuilder.append('{');
        queryDef.define(new ItemStockQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }
}
