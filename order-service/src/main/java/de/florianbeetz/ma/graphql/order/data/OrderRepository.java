package de.florianbeetz.ma.graphql.order.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByPaymentIdNullAndShipmentIdNotNull();

    List<OrderEntity> findAllByShipmentIdNull();

}
