// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class Address extends AbstractResponse<Address> {
    public Address() {
    }

    public Address(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "city": {
                    responseData.put(key, jsonAsString(field.getValue(), key));

                    break;
                }

                case "street": {
                    responseData.put(key, jsonAsString(field.getValue(), key));

                    break;
                }

                case "zip": {
                    responseData.put(key, jsonAsString(field.getValue(), key));

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
        return "Address";
    }

    public String getCity() {
        return (String) get("city");
    }

    public Address setCity(String arg) {
        optimisticData.put(getKey("city"), arg);
        return this;
    }

    public String getStreet() {
        return (String) get("street");
    }

    public Address setStreet(String arg) {
        optimisticData.put(getKey("street"), arg);
        return this;
    }

    public String getZip() {
        return (String) get("zip");
    }

    public Address setZip(String arg) {
        optimisticData.put(getKey("zip"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "city": return false;

            case "street": return false;

            case "zip": return false;

            default: return false;
        }
    }
}

