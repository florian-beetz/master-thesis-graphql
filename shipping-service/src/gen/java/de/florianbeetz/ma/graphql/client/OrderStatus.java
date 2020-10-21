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

public enum OrderStatus {
    CANCELED,

    CREATED,

    PAYMENT_RECEIVED,

    SHIPPED,

    UNKNOWN_VALUE;

    public static OrderStatus fromGraphQl(String value) {
        if (value == null) {
            return null;
        }

        switch (value) {
            case "CANCELED": {
                return CANCELED;
            }

            case "CREATED": {
                return CREATED;
            }

            case "PAYMENT_RECEIVED": {
                return PAYMENT_RECEIVED;
            }

            case "SHIPPED": {
                return SHIPPED;
            }

            default: {
                return UNKNOWN_VALUE;
            }
        }
    }
    public String toString() {
        switch (this) {
            case CANCELED: {
                return "CANCELED";
            }

            case CREATED: {
                return "CREATED";
            }

            case PAYMENT_RECEIVED: {
                return "PAYMENT_RECEIVED";
            }

            case SHIPPED: {
                return "SHIPPED";
            }

            default: {
                return "";
            }
        }
    }
}

