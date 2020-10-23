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

public class UpdateShipmentStatusResponseQuery extends Query<UpdateShipmentStatusResponseQuery> {
    UpdateShipmentStatusResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public UpdateShipmentStatusResponseQuery code() {
        startField("code");

        return this;
    }

    public UpdateShipmentStatusResponseQuery message() {
        startField("message");

        return this;
    }

    public UpdateShipmentStatusResponseQuery newStatus() {
        startField("newStatus");

        return this;
    }

    public UpdateShipmentStatusResponseQuery previousStatus() {
        startField("previousStatus");

        return this;
    }

    public UpdateShipmentStatusResponseQuery shipment(ShipmentQueryDefinition queryDef) {
        startField("shipment");

        _queryBuilder.append('{');
        queryDef.define(new ShipmentQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public UpdateShipmentStatusResponseQuery success() {
        startField("success");

        return this;
    }
}
