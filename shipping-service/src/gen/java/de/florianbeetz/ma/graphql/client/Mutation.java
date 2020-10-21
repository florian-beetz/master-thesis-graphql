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

                case "updateOrderStatus": {
                    UpdateStatusResponse optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new UpdateStatusResponse(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "createPayment": {
                    responseData.put(key, new CreatePaymentResponse(jsonAsObject(field.getValue(), key)));

                    break;
                }

                case "updatePaymentStatus": {
                    responseData.put(key, new UpdatePaymentStatusResponse(jsonAsObject(field.getValue(), key)));

                    break;
                }

                case "createShipment": {
                    responseData.put(key, new CreateShipmentResponse(jsonAsObject(field.getValue(), key)));

                    break;
                }

                case "updateShipmentStatus": {
                    responseData.put(key, new UpdateShipmentStatusResponse(jsonAsObject(field.getValue(), key)));

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

    public UpdateStatusResponse getUpdateOrderStatus() {
        return (UpdateStatusResponse) get("updateOrderStatus");
    }

    public Mutation setUpdateOrderStatus(UpdateStatusResponse arg) {
        optimisticData.put(getKey("updateOrderStatus"), arg);
        return this;
    }

    public CreatePaymentResponse getCreatePayment() {
        return (CreatePaymentResponse) get("createPayment");
    }

    public Mutation setCreatePayment(CreatePaymentResponse arg) {
        optimisticData.put(getKey("createPayment"), arg);
        return this;
    }

    public UpdatePaymentStatusResponse getUpdatePaymentStatus() {
        return (UpdatePaymentStatusResponse) get("updatePaymentStatus");
    }

    public Mutation setUpdatePaymentStatus(UpdatePaymentStatusResponse arg) {
        optimisticData.put(getKey("updatePaymentStatus"), arg);
        return this;
    }

    public CreateShipmentResponse getCreateShipment() {
        return (CreateShipmentResponse) get("createShipment");
    }

    public Mutation setCreateShipment(CreateShipmentResponse arg) {
        optimisticData.put(getKey("createShipment"), arg);
        return this;
    }

    public UpdateShipmentStatusResponse getUpdateShipmentStatus() {
        return (UpdateShipmentStatusResponse) get("updateShipmentStatus");
    }

    public Mutation setUpdateShipmentStatus(UpdateShipmentStatusResponse arg) {
        optimisticData.put(getKey("updateShipmentStatus"), arg);
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

            case "updateOrderStatus": return true;

            case "createPayment": return true;

            case "updatePaymentStatus": return true;

            case "createShipment": return true;

            case "updateShipmentStatus": return true;

            default: return false;
        }
    }
}

