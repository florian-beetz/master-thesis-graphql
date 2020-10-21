// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class OrderPosition extends AbstractResponse<OrderPosition> {
    public OrderPosition() {
    }

    public OrderPosition(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "amount": {
                    responseData.put(key, jsonAsInteger(field.getValue(), key).longValue());

                    break;
                }

                case "item": {
                    responseData.put(key, new Item(jsonAsObject(field.getValue(), key)));

                    break;
                }

                case "stock": {
                    List<ItemStockPosition> list1 = new ArrayList<>();
                    for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                        ItemStockPosition optional2 = null;
                        if (!element1.isJsonNull()) {
                            optional2 = new ItemStockPosition(jsonAsObject(element1, key));
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
        return "OrderPosition";
    }

    public Long getAmount() {
        return (Long) get("amount");
    }

    public OrderPosition setAmount(Long arg) {
        optimisticData.put(getKey("amount"), arg);
        return this;
    }

    public Item getItem() {
        return (Item) get("item");
    }

    public OrderPosition setItem(Item arg) {
        optimisticData.put(getKey("item"), arg);
        return this;
    }

    public List<ItemStockPosition> getStock() {
        return (List<ItemStockPosition>) get("stock");
    }

    public OrderPosition setStock(List<ItemStockPosition> arg) {
        optimisticData.put(getKey("stock"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "amount": return false;

            case "item": return true;

            case "stock": return true;

            default: return false;
        }
    }
}

