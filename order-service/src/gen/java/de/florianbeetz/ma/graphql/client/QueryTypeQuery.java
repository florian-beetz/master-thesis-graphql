// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.List;

import com.shopify.graphql.support.Arguments;
import com.shopify.graphql.support.Query;

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

        public ItemsArguments ids(List<Long> value) {
            if (value != null) {
                startArgument("ids");
                _queryBuilder.append('[');
                {
                    String listSeperator1 = "";
                    for (Long item1 : value) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                }
                _queryBuilder.append(']');
            }
            return this;
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

    public QueryTypeQuery payment(Long id, PaymentQueryDefinition queryDef) {
        startField("payment");

        _queryBuilder.append("(id:");
        Query.appendQuotedString(_queryBuilder, id.toString());

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new PaymentQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public QueryTypeQuery shipment(Long id, ShipmentQueryDefinition queryDef) {
        startField("shipment");

        _queryBuilder.append("(id:");
        Query.appendQuotedString(_queryBuilder, id.toString());

        _queryBuilder.append(')');

        _queryBuilder.append('{');
        queryDef.define(new ShipmentQuery(_queryBuilder));
        _queryBuilder.append('}');

        return this;
    }

    public String toString() {
        return _queryBuilder.toString();
    }
}
