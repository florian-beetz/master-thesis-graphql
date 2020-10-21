// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class ReservationResponseQuery extends Query<ReservationResponseQuery> {
    ReservationResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public ReservationResponseQuery code() {
        startField("code");

        return this;
    }

    public ReservationResponseQuery message() {
        startField("message");

        return this;
    }

    public ReservationResponseQuery positions(ReservationPositionQueryDefinition queryDef) {
        startField("positions");

        _queryBuilder.append('{');
        queryDef.define(new ReservationPositionQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public ReservationResponseQuery success() {
        startField("success");

        return this;
    }
}
