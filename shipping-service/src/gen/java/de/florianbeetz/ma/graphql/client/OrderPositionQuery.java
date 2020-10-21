// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class OrderPositionQuery extends Query<OrderPositionQuery> {
    OrderPositionQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public OrderPositionQuery amount() {
        startField("amount");

        return this;
    }

    public OrderPositionQuery item(ItemQueryDefinition queryDef) {
        startField("item");

        _queryBuilder.append('{');
        queryDef.define(new ItemQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public OrderPositionQuery stock(ItemStockPositionQueryDefinition queryDef) {
        startField("stock");

        _queryBuilder.append('{');
        queryDef.define(new ItemStockPositionQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }
}
