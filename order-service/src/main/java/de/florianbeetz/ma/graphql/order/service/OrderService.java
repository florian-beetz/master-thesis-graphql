package de.florianbeetz.ma.graphql.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.order.api.model.Item;
import de.florianbeetz.ma.graphql.order.api.model.ItemStock;
import de.florianbeetz.ma.graphql.order.api.model.ItemStockPosition;
import de.florianbeetz.ma.graphql.order.api.model.Order;
import de.florianbeetz.ma.graphql.order.api.model.OrderPosition;
import de.florianbeetz.ma.graphql.order.api.model.Payment;
import de.florianbeetz.ma.graphql.order.data.OrderEntity;
import de.florianbeetz.ma.graphql.order.data.OrderPositionEntity;
import de.florianbeetz.ma.graphql.order.data.OrderPositionRepository;
import de.florianbeetz.ma.graphql.order.data.OrderRepository;
import de.florianbeetz.ma.graphql.order.service.model.ItemPrice;
import de.florianbeetz.ma.graphql.order.service.model.OrderStatus;
import de.florianbeetz.ma.graphql.order.service.model.ReservationPosition;
import de.florianbeetz.ma.graphql.order.service.model.StatusUpdate;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;

    @Autowired
    public OrderService(InventoryService inventoryService, OrderRepository orderRepository, OrderPositionRepository orderPositionRepository) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
    }

    public Order lookupOrder(long id) {
        return orderRepository.findById(id)
                .map(this::fromEntity)
                .orElse(null);
    }

    public Order createOrder(List<de.florianbeetz.ma.graphql.order.service.model.OrderPosition> positions) throws ServiceException {
        List<ReservationPosition> reservationPositions = inventoryService.reserveItems(positions);
        log.debug("reserved items: {}", reservationPositions);

        val orderEntity = new OrderEntity();
        val savedOrder = orderRepository.save(orderEntity);

        List<OrderPositionEntity> positionEntities = new ArrayList<>();
        for (ReservationPosition reservationPosition : reservationPositions) {
            positionEntities.add(new OrderPositionEntity(null,
                    savedOrder,
                    reservationPosition.getAmount(),
                    reservationPosition.getItemId(),
                    reservationPosition.getItemStockId()));
        }
        orderPositionRepository.saveAll(positionEntities);

        log.debug("Saved order: {}", savedOrder);

        // TODO: create shipment and payment


        return fromEntity(savedOrder, positionEntities);
    }

    public StatusUpdate updateOrderStatus(long id, OrderStatus status) throws ServiceException {
        val orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new ServiceException(1, "Order does not exist."));

        val previousStatus = OrderStatus.from(orderEntity.getStatus());
        if (!OrderStatus.isValidStatusTransition(previousStatus, status)) {
            throw new ServiceException(2, "The transition to this state is not valid.");
        }

        orderEntity.setStatus(status.name());
        val savedEntity = orderRepository.save(orderEntity);

        return new StatusUpdate(previousStatus, status, fromEntity(savedEntity));
    }

    public List<Order> getOrdersWithoutPayment() {
        return orderRepository.findAllByPaymentIdNull().stream()
                       .map(this::fromEntity)
                       .collect(Collectors.toList());
    }

    public double getOrderTotal(Order order) throws ServiceException {
        Map<Long, Long> itemAmounts = new HashMap<>();
        List<Long> itemIds = new ArrayList<>();
        for (val position : order.getPositions()) {
            itemAmounts.put(position.getItem().getId(), position.getAmount());
            itemIds.add(position.getItem().getId());
        }

        // fetch item prices
        List<ItemPrice> itemPrices = inventoryService.getItemPrices(itemIds);

        double total = 0;
        for (val itemPrice : itemPrices) {
            total += itemPrice.getPrice() * itemAmounts.get(itemPrice.getItemId());
        }

        // TODO: add shipping cost

        return total;
    }
    
    public void updatePaymentId(Order order, long paymentId) throws ServiceException {
        val orderEntity = orderRepository.findById(order.getId())
                                         .orElseThrow(() -> new ServiceException(1, "Order does not exist."));
        orderEntity.setPaymentId(paymentId);
        orderRepository.save(orderEntity);
    }

    private Order fromEntity(OrderEntity orderEntity) {
        return fromEntity(orderEntity, null);
    }

    private Order fromEntity(OrderEntity orderEntity, List<OrderPositionEntity> positions) {
        if (positions == null) {
            positions = orderEntity.getPositions();
        }

        List<OrderPosition> orderPositions = positions.stream()
                                                             .collect(Collectors.groupingBy(OrderPositionEntity::getItemId))
                                                             .entrySet()
                                                             .stream()
                                                             .map(e -> new OrderPosition(
                                                                     new Item(e.getKey()),
                                                                     e.getValue().stream()
                                                                      .map(r -> new ItemStockPosition(
                                                                              r.getAmount(),
                                                                              new ItemStock(r.getItemStockId())))
                                                                      .collect(Collectors.toList())))
                                                             .collect(Collectors.toList());

        return new Order(orderEntity.getId(),
                orderPositions,
                de.florianbeetz.ma.graphql.order.api.model.OrderStatus.from(orderEntity.getStatus()),
                orderEntity.getPaymentId() == null ? null : new Payment(orderEntity.getPaymentId()));
    }
}
