// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.SchemaViolationError;

public class Warehouse extends AbstractResponse<Warehouse> {
    public Warehouse() {
    }

    public Warehouse(JsonObject fields) throws SchemaViolationError {
        for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
            String key = field.getKey();
            String fieldName = getFieldName(key);
            switch (fieldName) {
                case "id": {
                    responseData.put(key, Long.parseLong(jsonAsString(field.getValue(), key)));

                    break;
                }

                case "name": {
                    responseData.put(key, jsonAsString(field.getValue(), key));

                    break;
                }

                case "stock": {
                    List<ItemStock> optional1 = null;
                    if (!field.getValue().isJsonNull()) {
                        List<ItemStock> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            ItemStock optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new ItemStock(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        optional1 = list1;
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
        return "Warehouse";
    }

    public Long getId() {
        return (Long) get("id");
    }

    public Warehouse setId(Long arg) {
        optimisticData.put(getKey("id"), arg);
        return this;
    }

    public String getName() {
        return (String) get("name");
    }

    public Warehouse setName(String arg) {
        optimisticData.put(getKey("name"), arg);
        return this;
    }

    public List<ItemStock> getStock() {
        return (List<ItemStock>) get("stock");
    }

    public Warehouse setStock(List<ItemStock> arg) {
        optimisticData.put(getKey("stock"), arg);
        return this;
    }

    public boolean unwrapsToObject(String key) {
        switch (getFieldName(key)) {
            case "id": return false;

            case "name": return false;

            case "stock": return true;

            default: return false;
        }
    }
}

