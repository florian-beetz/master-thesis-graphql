// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class BookOutResponseQuery extends Query<BookOutResponseQuery> {
    BookOutResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public BookOutResponseQuery code() {
        startField("code");

        return this;
    }

    public BookOutResponseQuery message() {
        startField("message");

        return this;
    }

    public BookOutResponseQuery success() {
        startField("success");

        return this;
    }
}
