// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class OrderQuery extends Query<OrderQuery> {
    OrderQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public OrderQuery id() {
        startField("id");

        return this;
    }

    public OrderQuery payment(PaymentQueryDefinition queryDef) {
        startField("payment");

        _queryBuilder.append('{');
        queryDef.define(new PaymentQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public OrderQuery positions(OrderPositionQueryDefinition queryDef) {
        startField("positions");

        _queryBuilder.append('{');
        queryDef.define(new OrderPositionQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public OrderQuery status() {
        startField("status");

        return this;
    }
}
