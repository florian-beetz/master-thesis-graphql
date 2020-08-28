package de.florianbeetz.ma.graphql.inventory.api.model;

public interface MutationResponse {

    int getCode();
    boolean isSuccess();
    String getMessage();

}
