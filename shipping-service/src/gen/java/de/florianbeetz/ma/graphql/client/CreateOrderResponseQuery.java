// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class CreateOrderResponseQuery extends Query<CreateOrderResponseQuery> {
    CreateOrderResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public CreateOrderResponseQuery code() {
        startField("code");

        return this;
    }

    public CreateOrderResponseQuery message() {
        startField("message");

        return this;
    }

    public CreateOrderResponseQuery order(OrderQueryDefinition queryDef) {
        startField("order");

        _queryBuilder.append('{');
        queryDef.define(new OrderQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public CreateOrderResponseQuery success() {
        startField("success");

        return this;
    }
}
