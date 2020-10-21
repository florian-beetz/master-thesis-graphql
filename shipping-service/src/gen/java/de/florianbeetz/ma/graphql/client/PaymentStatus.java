// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

public enum PaymentStatus {
    CANCELLED,

    CREATED,

    PAYED,

    UNKNOWN_VALUE;

    public static PaymentStatus fromGraphQl(String value) {
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

            case "PAYED": {
                return PAYED;
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

            case PAYED: {
                return "PAYED";
            }

            default: {
                return "";
            }
        }
    }
}

