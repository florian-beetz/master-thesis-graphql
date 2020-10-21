// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class ReservationPosition extends AbstractResponse<ReservationPosition> {
    public ReservationPosition() {
    }

    public ReservationPosition(JsonObject fields) throws SchemaViolationError {
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
        return "ReservationPosition";
    }

    public Long getAmount() {
        return (Long) get("amount");
    }

    public ReservationPosition setAmount(Long arg) {
        optimisticData.put(getKey("amount"), arg);
        return this;
    }

    public Item getItem() {
        return (Item) get("item");
    }

    public ReservationPosition setItem(Item arg) {
        optimisticData.put(getKey("item"), arg);
        return this;
    }

    public ItemStock getStock() {
        return (ItemStock) get("stock");
    }

    public ReservationPosition setStock(ItemStock arg) {
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

