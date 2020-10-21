// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

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

