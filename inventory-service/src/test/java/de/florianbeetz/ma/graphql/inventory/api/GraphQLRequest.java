package de.florianbeetz.ma.graphql.inventory.api;

import java.security.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GraphQLRequest {

    private final String type;
    private final List<Object> fields = new ArrayList<>();

    private GraphQLRequest(String type) {
        this.type = type;
    }

    public static GraphQLRequest query() {
        return new GraphQLRequest("query");
    }

    public static GraphQLRequest mutation() {
        return new GraphQLRequest("mutation");
    }

    public GraphQLRequest field(String name, Function<SubQuery, SubQuery> sq) {
        SubQuery query = new SubQuery(name);
        fields.add(sq.apply(query));
        return this;
    }

    @Override
    public String toString() {
        return type + " {" +
                fields.stream().map(Object::toString).collect(Collectors.joining(" ")) +
                "}";
    }

    public static class SubQuery {

        private final String name;
        private final Map<String, Object> param = new HashMap<>();
        private final List<Object> fields = new ArrayList<>();

        public SubQuery(String name) {
            this.name = name;
        }

        public SubQuery param(String name, Object value) {
            param.put(name, value);
            return this;
        }

        public SubQuery scalar(String name) {
            fields.add(name);
            return this;
        }

        public SubQuery object(String name, Consumer<SubQuery> sq) {
            SubQuery query = new SubQuery(name);
            sq.accept(query);
            fields.add(query);
            return this;
        }

        @Override
        public String toString() {
            return name +
                    (param.isEmpty() ? "" : "(" + param.entrySet().stream().map(e -> e.getKey() + ":" + valueToString(e.getValue())).collect(Collectors.joining(",")) + ")") +
                    "{" + fields.stream().map(Object::toString).collect(Collectors.joining(" ")) + "}";
        }

        private String valueToString(Object value) {
            if (value instanceof String) {
                return "\"" + value + "\"";
            }
            return value.toString();
        }
    }
}
