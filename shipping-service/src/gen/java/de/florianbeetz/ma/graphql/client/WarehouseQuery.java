// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Arguments;
import com.shopify.graphql.support.Query;

public class WarehouseQuery extends Query<WarehouseQuery> {
    WarehouseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public WarehouseQuery id() {
        startField("id");

        return this;
    }

    public WarehouseQuery name() {
        startField("name");

        return this;
    }

    public class StockArguments extends Arguments {
        StockArguments(StringBuilder _queryBuilder) {
            super(_queryBuilder, true);
        }

        public StockArguments page(Long value) {
            if (value != null) {
                startArgument("page");
                _queryBuilder.append(value);
            }
            return this;
        }

        public StockArguments size(Long value) {
            if (value != null) {
                startArgument("size");
                _queryBuilder.append(value);
            }
            return this;
        }
    }

    public interface StockArgumentsDefinition {
        void define(StockArguments args);
    }

    public WarehouseQuery stock(ItemStockQueryDefinition queryDef) {
        return stock(args -> {}, queryDef);
    }

    public WarehouseQuery stock(StockArgumentsDefinition argsDef, ItemStockQueryDefinition queryDef) {
        startField("stock");

        StockArguments args = new StockArguments(_queryBuilder);
        argsDef.define(args);
        StockArguments.end(args);

        _queryBuilder.append('{');
        queryDef.define(new ItemStockQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }
}
