// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class ReservationResponse extends AbstractResponse<ReservationResponse> implements ApiResponse {
    public ReservationResponse() {
    }

    public ReservationResponse(JsonObject fields) throws SchemaViolationError {
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

                case "positions": {
                    List<ReservationPosition> optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        List<ReservationPosition> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            ReservationPosition optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new ReservationPosition(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        optional1 = list1;
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
        return "ReservationResponse";
    }

    public String getCode() {
        return (String) get("code");
    }

    public ReservationResponse setCode(String arg) {
        optimisticData.put(getKey("code"), arg);
        return this;
    }

    public String getMessage() {
        return (String) get("message");
    }

    public ReservationResponse setMessage(String arg) {
        optimisticData.put(getKey("message"), arg);
        return this;
    }

    public List<ReservationPosition> getPositions() {
        return (List<ReservationPosition>) get("positions");
    }

    public ReservationResponse setPositions(List<ReservationPosition> arg) {
        optimisticData.put(getKey("positions"), arg);
        return this;
    }

    public Boolean getSuccess() {
        return (Boolean) get("success");
    }

    public ReservationResponse setSuccess(Boolean arg) {
        optimisticData.put(getKey("success"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "code": return false;

            case "message": return false;

            case "positions": return true;

            case "success": return false;

            default: return false;
        }
    }
}

