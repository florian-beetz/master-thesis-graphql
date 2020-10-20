// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class UpdatePaymentStatusResponseQuery extends Query<UpdatePaymentStatusResponseQuery> {
    UpdatePaymentStatusResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public UpdatePaymentStatusResponseQuery code() {
        startField("code");

        return this;
    }

    public UpdatePaymentStatusResponseQuery message() {
        startField("message");

        return this;
    }

    public UpdatePaymentStatusResponseQuery newStatus() {
        startField("newStatus");

        return this;
    }

    public UpdatePaymentStatusResponseQuery payment(PaymentQueryDefinition queryDef) {
        startField("payment");

        _queryBuilder.append('{');
        queryDef.define(new PaymentQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public UpdatePaymentStatusResponseQuery previousStatus() {
        startField("previousStatus");

        return this;
    }

    public UpdatePaymentStatusResponseQuery success() {
        startField("success");

        return this;
    }
}
