// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class QueryType extends AbstractResponse<QueryType> {
    public QueryType() {
    }

    public QueryType(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "item": {
                    Item optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Item(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "items": {
                    List<Item> optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        List<Item> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Item optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Item(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        optional1 = list1;
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "warehouse": {
                    Warehouse optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Warehouse(jsonAsObject(field.getValue(), key));
                    }

                    responseData.put(key, optional1);

                    break;
                }

                case "warehouses": {
                    List<Warehouse> optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        List<Warehouse> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Warehouse optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Warehouse(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        optional1 = list1;
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

                case "payment": {
                    Payment optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        optional1 = new Payment(jsonAsObject(field.getValue(), key));
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
        return "QueryType";
    }

    public Item getItem() {
        return (Item) get("item");
    }

    public QueryType setItem(Item arg) {
        optimisticData.put(getKey("item"), arg);
        return this;
    }

    public List<Item> getItems() {
        return (List<Item>) get("items");
    }

    public QueryType setItems(List<Item> arg) {
        optimisticData.put(getKey("items"), arg);
        return this;
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get("warehouse");
    }

    public QueryType setWarehouse(Warehouse arg) {
        optimisticData.put(getKey("warehouse"), arg);
        return this;
    }

    public List<Warehouse> getWarehouses() {
        return (List<Warehouse>) get("warehouses");
    }

    public QueryType setWarehouses(List<Warehouse> arg) {
        optimisticData.put(getKey("warehouses"), arg);
        return this;
    }

    public Order getOrder() {
        return (Order) get("order");
    }

    public QueryType setOrder(Order arg) {
        optimisticData.put(getKey("order"), arg);
        return this;
    }

    public Payment getPayment() {
        return (Payment) get("payment");
    }

    public QueryType setPayment(Payment arg) {
        optimisticData.put(getKey("payment"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "item": return true;

            case "items": return true;

            case "warehouse": return true;

            case "warehouses": return true;

            case "order": return true;

            case "payment": return true;

            default: return false;
        }
    }
}

