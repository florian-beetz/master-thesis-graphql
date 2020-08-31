package de.florianbeetz.ma.graphql.inventory.api.model;

public interface ApiResponse {

    int getCode();
    boolean isSuccess();
    String getMessage();

}
