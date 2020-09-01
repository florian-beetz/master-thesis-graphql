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

public class ItemStockPosition extends AbstractResponse<ItemStockPosition> {
    public ItemStockPosition() {
    }

    public ItemStockPosition(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "amount": {
                    responseData.put(key, jsonAsInteger(field.getValue(), key).longValue());

                    break;
                }

                case "id": {
                    responseData.put(key, Long.parseLong(jsonAsString(field.getValue(), key)));

                    break;
                }

                case "stock": {
                    responseData.put(key, new ItemStock(jsonAsObject(field.getValue(), key)));

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
        return "ItemStockPosition";
    }

    public Long getAmount() {
        return (Long) get("amount");
    }

    public ItemStockPosition setAmount(Long arg) {
        optimisticData.put(getKey("amount"), arg);
        return this;
    }

    public Long getId() {
        return (Long) get("id");
    }

    public ItemStockPosition setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public ItemStock getStock() {
        return (ItemStock) get("stock");
    }

    public ItemStockPosition setStock(ItemStock arg) {
        optimisticData.put(getKey("stock"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "amount": return false;

            case "id": return false;

            case "stock": return true;

            default: return false;
        }
    }
}

