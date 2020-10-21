// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.SchemaViolationError;
import com.shopify.graphql.support.TopLevelResponse;

public class QueryResponse {
    private TopLevelResponse response;
    private QueryType data;

    public QueryResponse(TopLevelResponse response) throws SchemaViolationError {
        this.response = response;
        this.data = response.getData() != null ? new QueryType(response.getData()) : null;
    }

    public QueryType getData() {
        return data;
    }

    public List<Error> getErrors() {
        return response.getErrors();
    }

    public String toJson() {
        return new Gson().toJson(response);
    }

    public String prettyPrintJson() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(response);
    }

    public static QueryResponse fromJson(String json) throws SchemaViolationError {
        final TopLevelResponse response = new Gson().fromJson(json, TopLevelResponse.class);
        return new QueryResponse(response);
    }
}