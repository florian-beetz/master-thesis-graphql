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

public class UnknownApiResponse extends AbstractResponse<UnknownApiResponse> implements ApiResponse {
    public UnknownApiResponse() {
    }

    public UnknownApiResponse(JsonObject fields) throws SchemaViolationError {
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

    public static ApiResponse create(JsonObject fields) throws SchemaViolationError {
        String typeName = fields.getAsJsonPrimitive("__typename").getAsString();
        switch (typeName) {
            case "BookOutResponse": {
                return new BookOutResponse(fields);
            }

            case "CreateOrderResponse": {
                return new CreateOrderResponse(fields);
            }

            case "CreatePaymentResponse": {
                return new CreatePaymentResponse(fields);
            }

            case "CreateShipmentResponse": {
                return new CreateShipmentResponse(fields);
            }

            case "ReservationResponse": {
                return new ReservationResponse(fields);
            }

            case "UpdatePaymentStatusResponse": {
                return new UpdatePaymentStatusResponse(fields);
            }

            case "UpdateShipmentStatusResponse": {
                return new UpdateShipmentStatusResponse(fields);
            }

            case "UpdateStatusResponse": {
                return new UpdateStatusResponse(fields);
            }

            default: {
                return new UnknownApiResponse(fields);
            }
        }
    }

    public String getGraphQlTypeName() {
        return (String) get("__typename");
    }

    public String getCode() {
        return (String) get("code");
    }

    public UnknownApiResponse setCode(String arg) {
        optimisticData.put(getKey("code"), arg);
        return this;
    }

    public String getMessage() {
        return (String) get("message");
    }

    public UnknownApiResponse setMessage(String arg) {
        optimisticData.put(getKey("message"), arg);
        return this;
    }

    public Boolean getSuccess() {
        return (Boolean) get("success");
    }

    public UnknownApiResponse setSuccess(Boolean arg) {
        optimisticData.put(getKey("success"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "code": return false;

            case "message": return false;

            case "success": return false;

            default: return false;
        }
    }
}

