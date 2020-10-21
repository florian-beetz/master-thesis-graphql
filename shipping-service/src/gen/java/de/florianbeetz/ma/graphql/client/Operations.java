// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

public class Operations {
    public static QueryTypeQuery query(QueryTypeQueryDefinition queryDef) {
        StringBuilder queryString = new StringBuilder("{");
        QueryTypeQuery query = new QueryTypeQuery(queryString);
        queryDef.define(query);
        queryString.append('}');
        return query;
    }

    public static MutationQuery mutation(MutationQueryDefinition queryDef) {
        StringBuilder queryString = new StringBuilder("mutation{");
        MutationQuery query = new MutationQuery(queryString);
        queryDef.define(query);
        queryString.append('}');
        return query;
    }
}
