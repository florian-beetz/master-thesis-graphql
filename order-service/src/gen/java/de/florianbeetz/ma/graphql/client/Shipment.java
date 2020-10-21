// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class Shipment extends AbstractResponse<Shipment> {
    public Shipment() {
    }

    public Shipment(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "cost": {
                    Double optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = jsonAsDouble(field.getValue(), key);
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "destinationAddress": {
                    responseData.put(key, new Address(jsonAsObject(field.getValue(), key)));

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

                case "status": {
                    responseData.put(key, ShippingStatus.fromGraphQl(jsonAsString(field.getValue(), key)));

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
        return "Shipment";
    }

    public Double getCost() {
        return (Double) get("cost");
    }

    public Shipment setCost(Double arg) {
        optimisticData.put(getKey("cost"), arg);
        return this;
    }

    public Address getDestinationAddress() {
        return (Address) get("destinationAddress");
    }

    public Shipment setDestinationAddress(Address arg) {
        optimisticData.put(getKey("destinationAddress"), arg);
        return this;
    }

    public Long getId() {
        return (Long) get("id");
    }

    public Shipment setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public Order getOrder() {
        return (Order) get("order");
    }

    public Shipment setOrder(Order arg) {
        optimisticData.put(getKey("order"), arg);
        return this;
    }

    public ShippingStatus getStatus() {
        return (ShippingStatus) get("status");
    }

    public Shipment setStatus(ShippingStatus arg) {
        optimisticData.put(getKey("status"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "cost": return false;

            case "destinationAddress": return true;

            case "id": return false;

            case "order": return true;

            case "status": return false;

            default: return false;
        }
    }
}

