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

public class Mutation extends AbstractResponse<Mutation> {
    public Mutation() {
    }

    public Mutation(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "bookOutItems": {
                    responseData.put(key, new BookOutResponse(jsonAsObject(field.getValue(), key)));

                    break;
                }

                case "createItem": {
                    Item optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Item(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "createItemStock": {
                    ItemStock optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new ItemStock(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "createWarehouse": {
                    Warehouse optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Warehouse(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "reserveItems": {
                    ReservationResponse optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new ReservationResponse(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "createOrder": {
                    CreateOrderResponse optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new CreateOrderResponse(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

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
        return "Mutation";
    }

    public BookOutResponse getBookOutItems() {
        return (BookOutResponse) get("bookOutItems");
    }

    public Mutation setBookOutItems(BookOutResponse arg) {
        optimisticData.put(getKey("bookOutItems"), arg);
        return this;
    }

    public Item getCreateItem() {
        return (Item) get("createItem");
    }

    public Mutation setCreateItem(Item arg) {
        optimisticData.put(getKey("createItem"), arg);
        return this;
    }

    public ItemStock getCreateItemStock() {
        return (ItemStock) get("createItemStock");
    }

    public Mutation setCreateItemStock(ItemStock arg) {
        optimisticData.put(getKey("createItemStock"), arg);
        return this;
    }

    public Warehouse getCreateWarehouse() {
        return (Warehouse) get("createWarehouse");
    }

    public Mutation setCreateWarehouse(Warehouse arg) {
        optimisticData.put(getKey("createWarehouse"), arg);
        return this;
    }

    public ReservationResponse getReserveItems() {
        return (ReservationResponse) get("reserveItems");
    }

    public Mutation setReserveItems(ReservationResponse arg) {
        optimisticData.put(getKey("reserveItems"), arg);
        return this;
    }

    public CreateOrderResponse getCreateOrder() {
        return (CreateOrderResponse) get("createOrder");
    }

    public Mutation setCreateOrder(CreateOrderResponse arg) {
        optimisticData.put(getKey("createOrder"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "bookOutItems": return true;

            case "createItem": return true;

            case "createItemStock": return true;

            case "createWarehouse": return true;

            case "reserveItems": return true;

            case "createOrder": return true;

            default: return false;
        }
    }
}

