package de.florianbeetz.ma.graphql.order.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByPaymentIdNullAndShipmentIdNotNull();

    List<OrderEntity> findAllByShipmentIdNull();

    List<OrderEntity> findAllByStatus(String status);

    @Query("select entity from OrderEntity entity where entity.status=:status and (entity.shipmentId is not null or entity.paymentId is not null) ")
    List<OrderEntity> findAllByStatusAndHasSubResourceIds(String status);

}
