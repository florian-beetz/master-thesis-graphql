// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class ReservationPositionQuery extends Query<ReservationPositionQuery> {
    ReservationPositionQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public ReservationPositionQuery amount() {
        startField("amount");

        return this;
    }

    public ReservationPositionQuery item(ItemQueryDefinition queryDef) {
        startField("item");

        _queryBuilder.append('{');
        queryDef.define(new ItemQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public ReservationPositionQuery stock(ItemStockQueryDefinition queryDef) {
        startField("stock");

        _queryBuilder.append('{');
        queryDef.define(new ItemStockQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }
}
