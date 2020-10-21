// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.shopify.graphql.support.Query;

public class ApiResponseQuery extends Query<ApiResponseQuery> {
    ApiResponseQuery(StringBuilder _queryBuilder) {
        super(_queryBuilder);

        startField("__typename");
    }

    public ApiResponseQuery code() {
        startField("code");

        return this;
    }

    public ApiResponseQuery message() {
        startField("message");

        return this;
    }

    public ApiResponseQuery success() {
        startField("success");

        return this;
    }

    public ApiResponseQuery onBookOutResponse(BookOutResponseQueryDefinition queryDef) {
        startInlineFragment("BookOutResponse");
        queryDef.define(new BookOutResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }

    public ApiResponseQuery onCreateOrderResponse(CreateOrderResponseQueryDefinition queryDef) {
        startInlineFragment("CreateOrderResponse");
        queryDef.define(new CreateOrderResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }

    public ApiResponseQuery onCreatePaymentResponse(CreatePaymentResponseQueryDefinition queryDef) {
        startInlineFragment("CreatePaymentResponse");
        queryDef.define(new CreatePaymentResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }

    public ApiResponseQuery onCreateShipmentResponse(CreateShipmentResponseQueryDefinition queryDef) {
        startInlineFragment("CreateShipmentResponse");
        queryDef.define(new CreateShipmentResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }

    public ApiResponseQuery onReservationResponse(ReservationResponseQueryDefinition queryDef) {
        startInlineFragment("ReservationResponse");
        queryDef.define(new ReservationResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }

    public ApiResponseQuery onUpdatePaymentStatusResponse(UpdatePaymentStatusResponseQueryDefinition queryDef) {
        startInlineFragment("UpdatePaymentStatusResponse");
        queryDef.define(new UpdatePaymentStatusResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }

    public ApiResponseQuery onUpdateShipmentStatusResponse(UpdateShipmentStatusResponseQueryDefinition queryDef) {
        startInlineFragment("UpdateShipmentStatusResponse");
        queryDef.define(new UpdateShipmentStatusResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }

    public ApiResponseQuery onUpdateStatusResponse(UpdateStatusResponseQueryDefinition queryDef) {
        startInlineFragment("UpdateStatusResponse");
        queryDef.define(new UpdateStatusResponseQuery(_queryBuilder));
        _queryBuilder.append('}');
        return this;
    }
}
