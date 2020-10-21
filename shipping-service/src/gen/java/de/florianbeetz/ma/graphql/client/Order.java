// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class Order extends AbstractResponse<Order> {
    public Order() {
    }

    public Order(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "id": {
                    responseData.put(key, Long.parseLong(jsonAsString(field.getValue(), key)));

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

                case "positions": {
                    List<OrderPosition> list1 = new ArrayList<>();
                    for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                        OrderPosition optional2 = null;
                        if (!element1.isJsonNull()) {
                            optional2 = new OrderPosition(jsonAsObject(element1, key));
                        }

                        list1.add(optional2);
                    }

                    responseData.put(key, list1);

                    break;
                }

                case "status": {
                    OrderStatus optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = OrderStatus.fromGraphQl(jsonAsString(field.getValue(), key));
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
        return "Order";
    }

    public Long getId() {
        return (Long) get("id");
    }

    public Order setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public Payment getPayment() {
        return (Payment) get("payment");
    }

    public Order setPayment(Payment arg) {
        optimisticData.put(getKey("payment"), arg);
        return this;
    }

    public List<OrderPosition> getPositions() {
        return (List<OrderPosition>) get("positions");
    }

    public Order setPositions(List<OrderPosition> arg) {
        optimisticData.put(getKey("positions"), arg);
        return this;
    }

    public OrderStatus getStatus() {
        return (OrderStatus) get("status");
    }

    public Order setStatus(OrderStatus arg) {
        optimisticData.put(getKey("status"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "id": return false;

            case "payment": return true;

            case "positions": return true;

            case "status": return false;

            default: return false;
        }
    }
}

