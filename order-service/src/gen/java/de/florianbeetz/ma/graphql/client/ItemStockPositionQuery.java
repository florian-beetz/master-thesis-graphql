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

public class ItemStockPositionQuery extends Query<ItemStockPositionQuery> {
    ItemStockPositionQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public ItemStockPositionQuery amount() {
        startField("amount");

        return this;
    }

    public ItemStockPositionQuery id() {
        startField("id");

        return this;
    }

    public ItemStockPositionQuery stock(ItemStockQueryDefinition queryDef) {
        startField("stock");

        _queryBuilder.append('{');
        queryDef.define(new ItemStockQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }
}
