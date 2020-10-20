// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class CreatePaymentResponseQuery extends Query<CreatePaymentResponseQuery> {
    CreatePaymentResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public CreatePaymentResponseQuery code() {
        startField("code");

        return this;
    }

    public CreatePaymentResponseQuery message() {
        startField("message");

        return this;
    }

    public CreatePaymentResponseQuery payment(PaymentQueryDefinition queryDef) {
        startField("payment");

        _queryBuilder.append('{');
        queryDef.define(new PaymentQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public CreatePaymentResponseQuery success() {
        startField("success");

        return this;
    }
}
