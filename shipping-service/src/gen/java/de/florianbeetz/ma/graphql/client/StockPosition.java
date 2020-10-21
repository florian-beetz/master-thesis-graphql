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

public class StockPosition implements Serializable {
    private Long amount;

    private Long stockId;

    public StockPosition(Long amount, Long stockId) {
        this.amount = amount;

        this.stockId = stockId;
    }

    public Long getAmount() {
        return amount;
    }

    public StockPosition setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Long getStockId() {
        return stockId;
    }

    public StockPosition setStockId(Long stockId) {
        this.stockId = stockId;
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
        _queryBuilder.append("stockId:");
        Query.appendQuotedString(_queryBuilder, stockId.toString());

        _queryBuilder.append('}');
    }
}
