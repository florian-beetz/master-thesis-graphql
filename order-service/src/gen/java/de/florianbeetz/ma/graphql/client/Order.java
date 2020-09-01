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

public class Order extends AbstractResponse<Order> {
    public Order() {
    }

    public Order(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "id": {
                    responseData.put(key, Long.parseLong(jsonAsString(field.getValue(), key)));

                    break;
                }

                case "positions": {
                    List<OrderPosition> list1 = new ArrayList<>();
                    for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                        OrderPosition optional2 = null;
                        if (!element1.isJsonNull()) {
                            optional2 = new OrderPosition(jsonAsObject(element1, key));
                        }

                        list1.add(optional2);
                    }

                    responseData.put(key, list1);

                    break;
                }

                case "__typename": {
                    responseData.put(key, jsonAsString(field.getValue(), key));
                    break;
                }
                default: {
                    throw new SchemaViolationError(this, key, field.getValue());
                }
            }
        }
    }

    public String getGraphQlTypeName() {
        return "Order";
    }

    public Long getId() {
        return (Long) get("id");
    }

    public Order setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public List<OrderPosition> getPositions() {
        return (List<OrderPosition>) get("positions");
    }

    public Order setPositions(List<OrderPosition> arg) {
        optimisticData.put(getKey("positions"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "id": return false;

            case "positions": return true;

            default: return false;
        }
    }
}

