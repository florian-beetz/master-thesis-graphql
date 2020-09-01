// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.Arguments;
import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.Query;
import com.shopify.graphql.support.SchemaViolationError;
import com.shopify.graphql.support.TopLevelResponse;
import com.shopify.graphql.support.Input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderPositionInput implements Serializable {
    private Long amount;

    private Long itemId;

    public OrderPositionInput(Long amount, Long itemId) {
        this.amount = amount;

        this.itemId = itemId;
    }

    public Long getAmount() {
        return amount;
    }

    public OrderPositionInput setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public OrderPositionInput setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public void appendTo(StringBuilder _queryBuilder) {
        String separator = "";
        _queryBuilder.append('{');

        _queryBuilder.append(separator);
        separator = ",";
        _queryBuilder.append("amount:");
        _queryBuilder.append(amount);

        _queryBuilder.append(separator);
        separator = ",";
        _queryBuilder.append("itemId:");
        Query.appendQuotedString(_queryBuilder, itemId.toString());

        _queryBuilder.append('}');
    }
}
