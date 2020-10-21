// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class UpdateStatusResponseQuery extends Query<UpdateStatusResponseQuery> {
    UpdateStatusResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public UpdateStatusResponseQuery code() {
        startField("code");

        return this;
    }

    public UpdateStatusResponseQuery message() {
        startField("message");

        return this;
    }

    public UpdateStatusResponseQuery newStatus() {
        startField("newStatus");

        return this;
    }

    public UpdateStatusResponseQuery order(OrderQueryDefinition queryDef) {
        startField("order");

        _queryBuilder.append('{');
        queryDef.define(new OrderQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public UpdateStatusResponseQuery previousStatus() {
        startField("previousStatus");

        return this;
    }

    public UpdateStatusResponseQuery success() {
        startField("success");

        return this;
    }
}
