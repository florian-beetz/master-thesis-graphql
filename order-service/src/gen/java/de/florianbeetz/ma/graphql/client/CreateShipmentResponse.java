// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class CreateShipmentResponse extends AbstractResponse<CreateShipmentResponse> implements ApiResponse {
    public CreateShipmentResponse() {
    }

    public CreateShipmentResponse(JsonObject fields) throws SchemaViolationError {
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

                case "shipment": {
                    Shipment optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Shipment(jsonAsObject(field.getValue(), key));
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
        return "CreateShipmentResponse";
    }

    public String getCode() {
        return (String) get("code");
    }

    public CreateShipmentResponse setCode(String arg) {
        optimisticData.put(getKey("code"), arg);
        return this;
    }

    public String getMessage() {
        return (String) get("message");
    }

    public CreateShipmentResponse setMessage(String arg) {
        optimisticData.put(getKey("message"), arg);
        return this;
    }

    public Shipment getShipment() {
        return (Shipment) get("shipment");
    }

    public CreateShipmentResponse setShipment(Shipment arg) {
        optimisticData.put(getKey("shipment"), arg);
        return this;
    }

    public Boolean getSuccess() {
        return (Boolean) get("success");
    }

    public CreateShipmentResponse setSuccess(Boolean arg) {
        optimisticData.put(getKey("success"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "code": return false;

            case "message": return false;

            case "shipment": return true;

            case "success": return false;

            default: return false;
        }
    }
}

