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

public class UpdateStatusResponse extends AbstractResponse<UpdateStatusResponse> implements ApiResponse {
    public UpdateStatusResponse() {
    }

    public UpdateStatusResponse(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "code": {
                    responseData.put(key, jsonAsString(field.getValue(), key));

                    break;
                }

                case "message": {
                    String optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = jsonAsString(field.getValue(), key);
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "newStatus": {
                    OrderStatus optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = OrderStatus.fromGraphQl(jsonAsString(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "order": {
                    Order optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Order(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "previousStatus": {
                    OrderStatus optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = OrderStatus.fromGraphQl(jsonAsString(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "success": {
                    responseData.put(key, jsonAsBoolean(field.getValue(), key));

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
        return "UpdateStatusResponse";
    }

    public String getCode() {
        return (String) get("code");
    }

    public UpdateStatusResponse setCode(String arg) {
        optimisticData.put(getKey("code"), arg);
        return this;
    }

    public String getMessage() {
        return (String) get("message");
    }

    public UpdateStatusResponse setMessage(String arg) {
        optimisticData.put(getKey("message"), arg);
        return this;
    }

    public OrderStatus getNewStatus() {
        return (OrderStatus) get("newStatus");
    }

    public UpdateStatusResponse setNewStatus(OrderStatus arg) {
        optimisticData.put(getKey("newStatus"), arg);
        return this;
    }

    public Order getOrder() {
        return (Order) get("order");
    }

    public UpdateStatusResponse setOrder(Order arg) {
        optimisticData.put(getKey("order"), arg);
        return this;
    }

    public OrderStatus getPreviousStatus() {
        return (OrderStatus) get("previousStatus");
    }

    public UpdateStatusResponse setPreviousStatus(OrderStatus arg) {
        optimisticData.put(getKey("previousStatus"), arg);
        return this;
    }

    public Boolean getSuccess() {
        return (Boolean) get("success");
    }

    public UpdateStatusResponse setSuccess(Boolean arg) {
        optimisticData.put(getKey("success"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "code": return false;

            case "message": return false;

            case "newStatus": return false;

            case "order": return true;

            case "previousStatus": return false;

            case "success": return false;

            default: return false;
        }
    }
}

