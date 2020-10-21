package de.florianbeetz.ma.graphql.order.api;

import java.util.List;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.order.api.model.AddressInput;
import de.florianbeetz.ma.graphql.order.api.model.CreateOrderResponse;
import de.florianbeetz.ma.graphql.order.api.model.Order;
import de.florianbeetz.ma.graphql.order.api.model.OrderPositionInput;
import de.florianbeetz.ma.graphql.order.api.model.OrderStatus;
import de.florianbeetz.ma.graphql.order.api.model.UpdateStatusResponse;
import de.florianbeetz.ma.graphql.order.service.OrderService;
import de.florianbeetz.ma.graphql.order.service.ServiceException;
import de.florianbeetz.ma.graphql.order.service.model.OrderPosition;
import de.florianbeetz.ma.graphql.order.service.model.StatusUpdate;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Mutation implements GraphQLMutationResolver {

    private final OrderService orderService;

    @Autowired
    public Mutation(OrderService orderService) {
        this.orderService = orderService;
    }

    public CreateOrderResponse createOrder(List<OrderPositionInput> positions, AddressInput destinationAddress) {
        try {
            Order order = orderService.createOrder(toModel(positions), destinationAddress);
            return CreateOrderResponse.successful(order);
        } catch (ServiceException e) {
            log.error("Failed to create order.", e);
            return CreateOrderResponse.failure(e.getCode(), e.getMessage());
        }
    }

    public UpdateStatusResponse updateOrderStatus(long id, OrderStatus status) {
        try {
            StatusUpdate result = orderService.updateOrderStatus(id, status.toServiceModel());

            return UpdateStatusResponse.successful(OrderStatus.from(result.getPreviousStatus()), OrderStatus.from(result.getNewStatus()), result.getOrder());
        } catch (ServiceException e) {
            return UpdateStatusResponse.failure(e.getCode(), e.getMessage());
        }
    }

    private List<OrderPosition> toModel(List<OrderPositionInput> in) {
        return in.stream()
                .map(pos -> new OrderPosition(pos.getItemId(), pos.getAmount()))
                .collect(Collectors.toList());
    }

}
