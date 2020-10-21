// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class ItemStockPositionQuery extends Query<ItemStockPositionQuery> {
    ItemStockPositionQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public ItemStockPositionQuery amount() {
        startField("amount");

        return this;
    }

    public ItemStockPositionQuery stock(ItemStockQueryDefinition queryDef) {
        startField("stock");

        _queryBuilder.append('{');
        queryDef.define(new ItemStockQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }
}
