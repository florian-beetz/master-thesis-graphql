package de.florianbeetz.ma.graphql.payment.api.model;

import java.util.stream.Stream;

/**
 * @author Florian Beetz
 */
public enum PaymentStatus {

    CREATED(de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus.CREATED),
    PAYED(de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus.PAYED),
    CANCELLED(de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus.CANCELLED);

    private final de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus serviceModel;

    PaymentStatus(de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus serviceModel) {
        this.serviceModel = serviceModel;
    }

    public de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus toServiceModel() {
        return serviceModel;
    }

    public static PaymentStatus from(String value) {
        return Stream.of(PaymentStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }

    public static PaymentStatus from(de.florianbeetz.ma.graphql.payment.service.model.PaymentStatus serviceModel) {
        return Stream.of(PaymentStatus.values())
                .filter(status -> status.toServiceModel() == serviceModel)
                .findAny()
                .orElse(null);
    }

}
