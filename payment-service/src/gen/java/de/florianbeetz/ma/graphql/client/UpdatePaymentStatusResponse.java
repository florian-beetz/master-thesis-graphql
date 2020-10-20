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

public class UpdatePaymentStatusResponse extends AbstractResponse<UpdatePaymentStatusResponse> implements ApiResponse {
    public UpdatePaymentStatusResponse() {
    }

    public UpdatePaymentStatusResponse(JsonObject fields) throws SchemaViolationError {
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
                    PaymentStatus optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = PaymentStatus.fromGraphQl(jsonAsString(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "payment": {
                    Payment optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Payment(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "previousStatus": {
                    PaymentStatus optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = PaymentStatus.fromGraphQl(jsonAsString(field.getValue(), key));
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
        return "UpdatePaymentStatusResponse";
    }

    public String getCode() {
        return (String) get("code");
    }

    public UpdatePaymentStatusResponse setCode(String arg) {
        optimisticData.put(getKey("code"), arg);
        return this;
    }

    public String getMessage() {
        return (String) get("message");
    }

    public UpdatePaymentStatusResponse setMessage(String arg) {
        optimisticData.put(getKey("message"), arg);
        return this;
    }

    public PaymentStatus getNewStatus() {
        return (PaymentStatus) get("newStatus");
    }

    public UpdatePaymentStatusResponse setNewStatus(PaymentStatus arg) {
        optimisticData.put(getKey("newStatus"), arg);
        return this;
    }

    public Payment getPayment() {
        return (Payment) get("payment");
    }

    public UpdatePaymentStatusResponse setPayment(Payment arg) {
        optimisticData.put(getKey("payment"), arg);
        return this;
    }

    public PaymentStatus getPreviousStatus() {
        return (PaymentStatus) get("previousStatus");
    }

    public UpdatePaymentStatusResponse setPreviousStatus(PaymentStatus arg) {
        optimisticData.put(getKey("previousStatus"), arg);
        return this;
    }

    public Boolean getSuccess() {
        return (Boolean) get("success");
    }

    public UpdatePaymentStatusResponse setSuccess(Boolean arg) {
        optimisticData.put(getKey("success"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "code": return false;

            case "message": return false;

            case "newStatus": return false;

            case "payment": return true;

            case "previousStatus": return false;

            case "success": return false;

            default: return false;
        }
    }
}

