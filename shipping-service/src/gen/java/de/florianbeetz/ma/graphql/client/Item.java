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

public class Item extends AbstractResponse<Item> {
    public Item() {
    }

    public Item(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "description": {
                    String optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = jsonAsString(field.getValue(), key);
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "id": {
                    responseData.put(key, Long.parseLong(jsonAsString(field.getValue(), key)));

                    break;
                }

                case "price": {
                    responseData.put(key, jsonAsDouble(field.getValue(), key));

                    break;
                }

                case "stock": {
                    List<ItemStock> optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        List<ItemStock> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            ItemStock optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new ItemStock(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        optional1 = list1;
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "title": {
                    responseData.put(key, jsonAsString(field.getValue(), key));

                    break;
                }

                case "totalAvailable": {
                    responseData.put(key, jsonAsInteger(field.getValue(), key).longValue());

                    break;
                }

                case "totalInStock": {
                    responseData.put(key, jsonAsInteger(field.getValue(), key).longValue());

                    break;
                }

                case "weight": {
                    responseData.put(key, jsonAsDouble(field.getValue(), key));

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
        return "Item";
    }

    public String getDescription() {
        return (String) get("description");
    }

    public Item setDescription(String arg) {
        optimisticData.put(getKey("description"), arg);
        return this;
    }

    public Long getId() {
        return (Long) get("id");
    }

    public Item setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public Double getPrice() {
        return (Double) get("price");
    }

    public Item setPrice(Double arg) {
        optimisticData.put(getKey("price"), arg);
        return this;
    }

    public List<ItemStock> getStock() {
        return (List<ItemStock>) get("stock");
    }

    public Item setStock(List<ItemStock> arg) {
        optimisticData.put(getKey("stock"), arg);
        return this;
    }

    public String getTitle() {
        return (String) get("title");
    }

    public Item setTitle(String arg) {
        optimisticData.put(getKey("title"), arg);
        return this;
    }

    public Long getTotalAvailable() {
        return (Long) get("totalAvailable");
    }

    public Item setTotalAvailable(Long arg) {
        optimisticData.put(getKey("totalAvailable"), arg);
        return this;
    }

    public Long getTotalInStock() {
        return (Long) get("totalInStock");
    }

    public Item setTotalInStock(Long arg) {
        optimisticData.put(getKey("totalInStock"), arg);
        return this;
    }

    public Double getWeight() {
        return (Double) get("weight");
    }

    public Item setWeight(Double arg) {
        optimisticData.put(getKey("weight"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "description": return false;

            case "id": return false;

            case "price": return false;

            case "stock": return true;

            case "title": return false;

            case "totalAvailable": return false;

            case "totalInStock": return false;

            case "weight": return false;

            default: return false;
        }
    }
}

