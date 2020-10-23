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

public enum ShippingStatus {
    CANCELLED,

    CREATED,

    READY_TO_SHIP,

    SHIPPED,

    UNKNOWN_VALUE;

    public static ShippingStatus fromGraphQl(String value) {
        if (value == null) {
            return null;
        }

        switch (value) {
            case "CANCELLED": {
                return CANCELLED;
            }

            case "CREATED": {
                return CREATED;
            }

            case "READY_TO_SHIP": {
                return READY_TO_SHIP;
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
            case CANCELLED: {
                return "CANCELLED";
            }

            case CREATED: {
                return "CREATED";
            }

            case READY_TO_SHIP: {
                return "READY_TO_SHIP";
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

