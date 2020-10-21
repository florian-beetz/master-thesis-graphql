// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

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
