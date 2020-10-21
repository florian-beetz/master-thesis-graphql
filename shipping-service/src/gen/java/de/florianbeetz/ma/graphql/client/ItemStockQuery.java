// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class ItemStockQuery extends Query<ItemStockQuery> {
    ItemStockQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public ItemStockQuery available() {
        startField("available");

        return this;
    }

    public ItemStockQuery id() {
        startField("id");

        return this;
    }

    public ItemStockQuery inStock() {
        startField("inStock");

        return this;
    }

    public ItemStockQuery item(ItemQueryDefinition queryDef) {
        startField("item");

        _queryBuilder.append('{');
        queryDef.define(new ItemQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public ItemStockQuery warehouse(WarehouseQueryDefinition queryDef) {
        startField("warehouse");

        _queryBuilder.append('{');
        queryDef.define(new WarehouseQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }
}
