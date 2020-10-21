// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class AddressQuery extends Query<AddressQuery> {
    AddressQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public AddressQuery city() {
        startField("city");

        return this;
    }

    public AddressQuery street() {
        startField("street");

        return this;
    }

    public AddressQuery zip() {
        startField("zip");

        return this;
    }
}
