// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

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

