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

public class Payment extends AbstractResponse<Payment> {
    public Payment() {
    }

    public Payment(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "amount": {
                    responseData.put(key, jsonAsDouble(field.getValue(), key));

                    break;
                }

                case "id": {
                    responseData.put(key, Long.parseLong(jsonAsString(field.getValue(), key)));

                    break;
                }

                case "order": {
                    responseData.put(key, new Order(jsonAsObject(field.getValue(), key)));

                    break;
                }

                case "reference": {
                    responseData.put(key, jsonAsString(field.getValue(), key));

                    break;
                }

                case "status": {
                    responseData.put(key, PaymentStatus.fromGraphQl(jsonAsString(field.getValue(), key)));

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
        return "Payment";
    }

    public Double getAmount() {
        return (Double) get("amount");
    }

    public Payment setAmount(Double arg) {
        optimisticData.put(getKey("amount"), arg);
        return this;
    }

    public Long getId() {
        return (Long) get("id");
    }

    public Payment setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public Order getOrder() {
        return (Order) get("order");
    }

    public Payment setOrder(Order arg) {
        optimisticData.put(getKey("order"), arg);
        return this;
    }

    public String getReference() {
        return (String) get("reference");
    }

    public Payment setReference(String arg) {
        optimisticData.put(getKey("reference"), arg);
        return this;
    }

    public PaymentStatus getStatus() {
        return (PaymentStatus) get("status");
    }

    public Payment setStatus(PaymentStatus arg) {
        optimisticData.put(getKey("status"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "amount": return false;

            case "id": return false;

            case "order": return true;

            case "reference": return false;

            case "status": return false;

            default: return false;
        }
    }
}

