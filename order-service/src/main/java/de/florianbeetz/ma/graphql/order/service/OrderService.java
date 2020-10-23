package de.florianbeetz.ma.graphql.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.florianbeetz.ma.graphql.order.api.model.AddressInput;
import de.florianbeetz.ma.graphql.order.api.model.Item;
import de.florianbeetz.ma.graphql.order.api.model.ItemStock;
import de.florianbeetz.ma.graphql.order.api.model.ItemStockPosition;
import de.florianbeetz.ma.graphql.order.api.model.Order;
import de.florianbeetz.ma.graphql.order.api.model.OrderPosition;
import de.florianbeetz.ma.graphql.order.api.model.Payment;
import de.florianbeetz.ma.graphql.order.api.model.Shipment;
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
    private final ShopApiService shopApiService;

    @Autowired
    public OrderService(InventoryService inventoryService, OrderRepository orderRepository, OrderPositionRepository orderPositionRepository, ShopApiService shopApiService) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.shopApiService = shopApiService;
    }

    public Order lookupOrder(long id) {
        return orderRepository.findById(id)
                .map(this::fromEntity)
                .orElse(null);
    }

    public Order createOrder(List<de.florianbeetz.ma.graphql.order.service.model.OrderPosition> positions, AddressInput address) throws ServiceException {
        List<ReservationPosition> reservationPositions = inventoryService.reserveItems(positions);
        log.debug("reserved items: {}", reservationPositions);

        val orderEntity = new OrderEntity(address.getStreet(), address.getCity(), address.getZip());
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
        return fromEntity(savedOrder, positionEntities);
    }

    public StatusUpdate updateOrderStatus(long id, OrderStatus status) throws ServiceException {
        val orderEntity = loadById(id);

        val previousStatus = OrderStatus.from(orderEntity.getStatus());
        if (!OrderStatus.isValidStatusTransition(previousStatus, status)) {
            throw new ServiceException(2, "The transition to this state is not valid.");
        }

        orderEntity.setStatus(status.name());
        val savedEntity = orderRepository.save(orderEntity);

        return new StatusUpdate(previousStatus, status, fromEntity(savedEntity));
    }

    public List<Order> getOrdersWithoutPayment() {
        return orderRepository.findAllByPaymentIdNullAndShipmentIdNotNull().stream()
                       .map(this::fromEntity)
                       .collect(Collectors.toList());
    }

    public List<Order> getOrdersWithoutShipment() {
        return orderRepository.findAllByShipmentIdNull().stream()
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

        try {
            total += shopApiService.getShipmentCost(order.getShipment().getId());
        } catch (ApiException e) {
            throw new ServiceException(20, "Failed to calculate shipping cost.", e);
        }

        return total;
    }
    
    public void updatePaymentId(Order order, long paymentId) throws ServiceException {
        val orderEntity = loadById(order.getId());
        orderEntity.setPaymentId(paymentId);
        orderRepository.save(orderEntity);
    }

    public void updateShipmentId(Order order, long shipmentId) throws ServiceException {
        val orderEntity = loadById(order.getId());
        orderEntity.setShipmentId(shipmentId);
        orderRepository.save(orderEntity);
    }

    public List<Order> getPayedOrders() {
        return orderRepository.findAllByStatus(OrderStatus.PAYMENT_RECEIVED.name()).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Order> getUpdatableCancelledOrders() {
        return orderRepository.findAllByStatusAndHasSubResourceIds(OrderStatus.CANCELED.name()).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public void removePayment(Order order) throws ServiceException {
        val entity = loadById(order.getId());
        entity.setPaymentId(null);
        orderRepository.save(entity);
    }

    public void removeShipment(Order order) throws ServiceException {
        val entity = loadById(order.getId());
        entity.setShipmentId(null);
        orderRepository.save(entity);
    }

    public List<Order> getUpdatableShippedOrders() {
        return orderRepository.findAllByStatusAndItemsBookedOutIsFalse(OrderStatus.SHIPPED.name()).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Order> getCancellingOrders() {
        return orderRepository.findAllByStatusAndItemsBookedOutIsFalse(OrderStatus.CANCELED.name()).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ReservationPosition> getReservationPositions(Order order) {
        List<ReservationPosition> positions = new ArrayList<>();

        for (val position : order.getPositions()) {
            for (val itemStock : position.getStock()) {
                positions.add(new ReservationPosition(position.getItem().getId(), itemStock.getStock().getId(), itemStock.getAmount()));
            }
        }

        return positions;
    }

    Map<Long, Long> getReservationPositionsMap(Order order) {
        return order.getPositions().stream().flatMap(pos -> pos.getStock().stream())
                .collect(Collectors.toMap(pos -> pos.getStock().getId(), ItemStockPosition::getAmount));
    }

    void setItemsBookedOut(Order order) throws ServiceException {
        val entity = loadById(order.getId());
        entity.setItemsBookedOut(true);
        orderRepository.save(entity);
    }

    private OrderEntity loadById(long id) throws ServiceException {
        return orderRepository.findById(id)
                              .orElseThrow(() -> new ServiceException(1, "Order does not exist."));
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
                orderEntity.getPaymentId() == null ? null : new Payment(orderEntity.getPaymentId()),
                orderEntity.getShipmentId() == null ? null : new Shipment(orderEntity.getShipmentId()),
                orderEntity.getDeliveryStreet(),
                orderEntity.getDeliveryCity(),
                orderEntity.getDeliveryZip());
    }
}
