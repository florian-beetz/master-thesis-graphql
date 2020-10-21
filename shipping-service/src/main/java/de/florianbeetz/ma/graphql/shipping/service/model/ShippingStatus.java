package de.florianbeetz.ma.graphql.shipping.service.model;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Florian Beetz
 */
public enum ShippingStatus {

    CREATED,
    READY_TO_SHIP,
    SHIPPED,
    CANCELLED

    ;

    private static final Map<ShippingStatus, Set<ShippingStatus>> VALID_TRANSITIONS = Map.of(
            CREATED, Set.of(READY_TO_SHIP, CANCELLED),
            READY_TO_SHIP, Set.of(SHIPPED, CANCELLED),
            SHIPPED, Collections.emptySet(),
            CANCELLED, Collections.emptySet()
    );

    public static boolean isValidTransition(ShippingStatus from, ShippingStatus to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        return VALID_TRANSITIONS.get(from).contains(to);
    }

    public static ShippingStatus from(String value) {
        return Stream.of(ShippingStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }
}
