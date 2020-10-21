// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

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
