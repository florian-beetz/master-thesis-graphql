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

public class ItemStock extends AbstractResponse<ItemStock> {
    public ItemStock() {
    }

    public ItemStock(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "available": {
                    Long optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = jsonAsInteger(field.getValue(), key).longValue();
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "id": {
                    responseData.put(key, Long.parseLong(jsonAsString(field.getValue(), key)));

                    break;
                }

                case "inStock": {
                    Long optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = jsonAsInteger(field.getValue(), key).longValue();
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "item": {
                    responseData.put(key, new Item(jsonAsObject(field.getValue(), key)));

                    break;
                }

                case "warehouse": {
                    responseData.put(key, new Warehouse(jsonAsObject(field.getValue(), key)));

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
        return "ItemStock";
    }

    public Long getAvailable() {
        return (Long) get("available");
    }

    public ItemStock setAvailable(Long arg) {
        optimisticData.put(getKey("available"), arg);
        return this;
    }

    public Long getId() {
        return (Long) get("id");
    }

    public ItemStock setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public Long getInStock() {
        return (Long) get("inStock");
    }

    public ItemStock setInStock(Long arg) {
        optimisticData.put(getKey("inStock"), arg);
        return this;
    }

    public Item getItem() {
        return (Item) get("item");
    }

    public ItemStock setItem(Item arg) {
        optimisticData.put(getKey("item"), arg);
        return this;
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get("warehouse");
    }

    public ItemStock setWarehouse(Warehouse arg) {
        optimisticData.put(getKey("warehouse"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "available": return false;

            case "id": return false;

            case "inStock": return false;

            case "item": return true;

            case "warehouse": return true;

            default: return false;
        }
    }
}

