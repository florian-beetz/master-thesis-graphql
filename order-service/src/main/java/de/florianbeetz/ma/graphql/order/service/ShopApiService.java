package de.florianbeetz.ma.graphql.order.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.SchemaViolationError;
import de.florianbeetz.ma.graphql.client.BookOutInput;
import de.florianbeetz.ma.graphql.client.BookOutResponse;
import de.florianbeetz.ma.graphql.client.ItemStockQuery;
import de.florianbeetz.ma.graphql.client.Mutation;
import de.florianbeetz.ma.graphql.client.MutationQuery;
import de.florianbeetz.ma.graphql.client.MutationResponse;
import de.florianbeetz.ma.graphql.client.Operations;
import de.florianbeetz.ma.graphql.client.QueryResponse;
import de.florianbeetz.ma.graphql.client.QueryType;
import de.florianbeetz.ma.graphql.client.QueryTypeQuery;
import de.florianbeetz.ma.graphql.client.ReservationResponse;
import de.florianbeetz.ma.graphql.client.StockPosition;
import de.florianbeetz.ma.graphql.order.service.model.ReservationPosition;
import lombok.val;
import org.codehaus.jackson.JsonNode;
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

    public List<ReservationPosition> reserveItem(long itemId, long amount) throws ApiException {
        MutationQuery query = Operations.mutation(mutation -> mutation
                .reserveItems(amount, itemId, response ->
                        response.code()
                                .message()
                                .success()
                                .positions(position ->
                                        position.amount()
                                                .stock(ItemStockQuery::id))));

        ReservationResponse reservationResponse = mutate(query).getReserveItems();

        if (!reservationResponse.getSuccess()) {
            throw new ApiException("Failed to reserve items: " + reservationResponse.getMessage() + " (code=" + reservationResponse.getCode() + ")");
        }

        return reservationResponse
                .getPositions()
                .stream()
                .map(p -> new ReservationPosition(itemId, p.getStock().getId(), p.getAmount()))
                .collect(Collectors.toList());
    }

    public void bookOutItems(Map<Long, Long> positions) throws ApiException {
        val input = new BookOutInput(positions.entrySet().stream()
                                              .map(e -> new StockPosition(e.getValue(), e.getKey()))
                                              .collect(Collectors.toList()));

        MutationQuery query = Operations.mutation(mutation -> mutation
                .bookOutItems(input, res -> res
                        .code()
                        .message()
                        .success()));

        BookOutResponse bookOutResponse = mutate(query).getBookOutItems();
        if (!bookOutResponse.getSuccess()) {
            throw new ApiException("Failed to book out items: " + bookOutResponse.getMessage() + " (code=" + bookOutResponse.getCode() + ")");
        }
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
