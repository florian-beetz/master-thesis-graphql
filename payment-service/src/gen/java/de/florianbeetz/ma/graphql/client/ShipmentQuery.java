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

public class ShipmentQuery extends Query<ShipmentQuery> {
    ShipmentQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public ShipmentQuery cost() {
        startField("cost");

        return this;
    }

    public ShipmentQuery destinationAddress(AddressQueryDefinition queryDef) {
        startField("destinationAddress");

        _queryBuilder.append('{');
        queryDef.define(new AddressQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public ShipmentQuery id() {
        startField("id");

        return this;
    }

    public ShipmentQuery order(OrderQueryDefinition queryDef) {
        startField("order");

        _queryBuilder.append('{');
        queryDef.define(new OrderQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public ShipmentQuery status() {
        startField("status");

        return this;
    }
}
