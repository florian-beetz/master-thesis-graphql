// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.io.Serializable;

import com.shopify.graphql.support.Query;

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
