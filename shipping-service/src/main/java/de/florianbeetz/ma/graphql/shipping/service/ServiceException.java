package de.florianbeetz.ma.graphql.shipping.service;

import lombok.Getter;

/**
 * Represents an error that should be reported to the API consumer.
 */
public class ServiceException extends Exception {

    @Getter
    private final int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
