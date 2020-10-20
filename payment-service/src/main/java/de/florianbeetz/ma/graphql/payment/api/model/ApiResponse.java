package de.florianbeetz.ma.graphql.payment.api.model;

public interface ApiResponse {

    int getCode();
    boolean isSuccess();
    String getMessage();

}
