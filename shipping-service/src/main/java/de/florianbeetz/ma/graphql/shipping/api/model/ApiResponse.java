package de.florianbeetz.ma.graphql.shipping.api.model;

public interface ApiResponse {

    int getCode();
    boolean isSuccess();
    String getMessage();

}
