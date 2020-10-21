// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.io.Serializable;

import com.shopify.graphql.support.Query;

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
