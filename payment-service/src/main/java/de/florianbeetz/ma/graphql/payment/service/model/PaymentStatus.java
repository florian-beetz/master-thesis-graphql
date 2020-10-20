package de.florianbeetz.ma.graphql.payment.service.model;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Florian Beetz
 */
public enum PaymentStatus {

    CREATED,

    PAYED,

    CANCELLED

    ;

    private static final Map<PaymentStatus, Set<PaymentStatus>> VALID_TRANSITIONS = Map.of(
            CREATED, Set.of(PAYED, CANCELLED),
            PAYED, Collections.emptySet(),
            CANCELLED, Collections.emptySet()
    );

    public static PaymentStatus from(String value) {
        return Stream.of(PaymentStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }

    public static boolean isValidTransition(PaymentStatus from, PaymentStatus to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        return VALID_TRANSITIONS.get(from).contains(to);
    }

}
