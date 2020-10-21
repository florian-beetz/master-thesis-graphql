// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class CreateShipmentResponseQuery extends Query<CreateShipmentResponseQuery> {
    CreateShipmentResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public CreateShipmentResponseQuery code() {
        startField("code");

        return this;
    }

    public CreateShipmentResponseQuery message() {
        startField("message");

        return this;
    }

    public CreateShipmentResponseQuery shipment(ShipmentQueryDefinition queryDef) {
        startField("shipment");

        _queryBuilder.append('{');
        queryDef.define(new ShipmentQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public CreateShipmentResponseQuery success() {
        startField("success");

        return this;
    }
}
