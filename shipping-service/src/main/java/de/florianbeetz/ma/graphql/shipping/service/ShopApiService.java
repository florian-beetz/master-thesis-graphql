package de.florianbeetz.ma.graphql.shipping.service;

import java.util.List;
import java.util.stream.Collectors;

import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.SchemaViolationError;
import de.florianbeetz.ma.graphql.client.Mutation;
import de.florianbeetz.ma.graphql.client.MutationQuery;
import de.florianbeetz.ma.graphql.client.MutationResponse;
import de.florianbeetz.ma.graphql.client.Operations;
import de.florianbeetz.ma.graphql.client.Order;
import de.florianbeetz.ma.graphql.client.OrderStatus;
import de.florianbeetz.ma.graphql.client.QueryResponse;
import de.florianbeetz.ma.graphql.client.QueryType;
import de.florianbeetz.ma.graphql.client.QueryTypeQuery;
import de.florianbeetz.ma.graphql.client.UpdateStatusResponse;
import lombok.val;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Florian Beetz
 */
@Service
public class ShopApiService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public ShopApiService(RestTemplate restTemplate,
                          @Value("${application.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public OrderStatus getOrderStatus(long orderId) throws ApiException {
        @SuppressWarnings("Convert2MethodRef")
        val query = Operations.query(q -> q
                .order(orderId, order -> order
                        .status()));

        val order = this.query(query).getOrder();
        if (order == null) {
            return null;
        }

        return order.getStatus();
    }

    public void updateOrderStatus(long orderId, OrderStatus status) throws ApiException {
        val query = Operations.mutation(mutation -> mutation
                .updateOrderStatus(orderId, status, response -> response
                        .code()
                        .success()
                        .message()));

        UpdateStatusResponse response = mutate(query).getUpdateOrderStatus();

        if (!response.getSuccess()) {
            throw new ApiException("Failed to update order status: " + response.getMessage() + " (code=" + response.getCode() + ")");
        }
    }

    public double getOrderWeight(long orderId) throws ApiException {
        @SuppressWarnings("Convert2MethodRef")
        val query = Operations.query(q -> q
                .order(orderId, order -> order
                        .positions(position -> position
                                .amount()
                                .item(item -> item
                                        .weight()))));

        Order order = this.query(query).getOrder();

        if (order == null) {
            throw new ApiException("Order not found.");
        }

        return order.getPositions().stream()
             .mapToDouble(pos -> pos.getAmount() * pos.getItem().getWeight())
             .sum();
    }

    private QueryType query(QueryTypeQuery query) throws ApiException {
        try {
            val response = exchangeQuery(query.toString());

            val queryResponse = QueryResponse.fromJson(response.getBody());

            if (queryResponse.getErrors() != null && !queryResponse.getErrors().isEmpty()) {
                throw new ApiException("Failed to query API: " + queryResponse.getErrors().stream().map(Error::message).collect(Collectors.joining(", ")));
            }

            return queryResponse.getData();
        } catch (SchemaViolationError e) {
            throw new ApiException("Failed to parse API response.", e);
        } catch (HttpClientErrorException e) {
            throw new ApiException("API responded with error: " + e.getStatusCode(), e);
        }
    }

    private Mutation mutate(MutationQuery query) throws ApiException {
        try {
            val response = exchangeQuery(query.toString());

            val mutationResponse = MutationResponse.fromJson(response.getBody());

            if (mutationResponse.getErrors() != null && !mutationResponse.getErrors().isEmpty()) {
                throw new ApiException("Failed to query API: " + mutationResponse.getErrors().stream().map(Error::message).collect(Collectors.joining(", ")));
            }

            return mutationResponse.getData();
        } catch (SchemaViolationError e) {
            throw new ApiException("Failed to parse API response.", e);
        } catch (HttpClientErrorException e) {
            throw new ApiException("API responded with error: " + e.getStatusCode(), e);
        }
    }

    private ResponseEntity<String> exchangeQuery(String query) {
        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        val entity = new HttpEntity<>(queryToJson(query), headers);
        return restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);
    }

    private String queryToJson(String query) {
        ObjectNode node = OBJECT_MAPPER.createObjectNode();
        node.put("query", query);

        return node.toString();
    }

}
