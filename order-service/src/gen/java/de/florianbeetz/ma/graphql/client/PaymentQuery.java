// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class PaymentQuery extends Query<PaymentQuery> {
    PaymentQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public PaymentQuery amount() {
        startField("amount");

        return this;
    }

    public PaymentQuery id() {
        startField("id");

        return this;
    }

    public PaymentQuery order(OrderQueryDefinition queryDef) {
        startField("order");

        _queryBuilder.append('{');
        queryDef.define(new OrderQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public PaymentQuery reference() {
        startField("reference");

        return this;
    }

    public PaymentQuery status() {
        startField("status");

        return this;
    }
}
