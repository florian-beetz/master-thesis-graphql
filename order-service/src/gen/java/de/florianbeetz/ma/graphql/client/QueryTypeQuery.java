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

public class QueryTypeQuery extends Query<QueryTypeQuery> {
    QueryTypeQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);
    }

    public QueryTypeQuery item(Long id, ItemQueryDefinition queryDef) {
        startField("item");

        _queryBuilder.append("(id:");
        Query.appendQuotedString(_queryBuilder, id.toString());

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new ItemQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public class ItemsArguments extends Arguments {
        ItemsArguments(StringBuilder _queryBuilder) {
            super(_queryBuilder, true);
        }

        public ItemsArguments page(Long value) {
            if (value != null) {
                startArgument("page");
                _queryBuilder.append(value);
            }
            return this;
        }

        public ItemsArguments size(Long value) {
            if (value != null) {
                startArgument("size");
                _queryBuilder.append(value);
            }
            return this;
        }
    }

    public interface ItemsArgumentsDefinition {
        void define(ItemsArguments args);
    }

    public QueryTypeQuery items(ItemQueryDefinition queryDef) {
        return items(args -> {}, queryDef);
    }

    public QueryTypeQuery items(ItemsArgumentsDefinition argsDef, ItemQueryDefinition queryDef) {
        startField("items");

        ItemsArguments args = new ItemsArguments(_queryBuilder);
        argsDef.define(args);
        ItemsArguments.end(args);

        _queryBuilder.append('{');
        queryDef.define(new ItemQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public QueryTypeQuery warehouse(Long id, WarehouseQueryDefinition queryDef) {
        startField("warehouse");

        _queryBuilder.append("(id:");
        Query.appendQuotedString(_queryBuilder, id.toString());

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new WarehouseQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public class WarehousesArguments extends Arguments {
        WarehousesArguments(StringBuilder _queryBuilder) {
            super(_queryBuilder, true);
        }

        public WarehousesArguments page(Long value) {
            if (value != null) {
                startArgument("page");
                _queryBuilder.append(value);
            }
            return this;
        }

        public WarehousesArguments size(Long value) {
            if (value != null) {
                startArgument("size");
                _queryBuilder.append(value);
            }
            return this;
        }
    }

    public interface WarehousesArgumentsDefinition {
        void define(WarehousesArguments args);
    }

    public QueryTypeQuery warehouses(WarehouseQueryDefinition queryDef) {
        return warehouses(args -> {}, queryDef);
    }

    public QueryTypeQuery warehouses(WarehousesArgumentsDefinition argsDef, WarehouseQueryDefinition queryDef) {
        startField("warehouses");

        WarehousesArguments args = new WarehousesArguments(_queryBuilder);
        argsDef.define(args);
        WarehousesArguments.end(args);

        _queryBuilder.append('{');
        queryDef.define(new WarehouseQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public QueryTypeQuery order(Long id, OrderQueryDefinition queryDef) {
        startField("order");

        _queryBuilder.append("(id:");
        Query.appendQuotedString(_queryBuilder, id.toString());

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new OrderQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public String toString() {
        return _queryBuilder.toString();
    }
}
