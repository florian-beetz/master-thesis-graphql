package de.florianbeetz.ma.graphql.order.api.model;

public interface ApiResponse {

    int getCode();
    boolean isSuccess();
    String getMessage();

}
