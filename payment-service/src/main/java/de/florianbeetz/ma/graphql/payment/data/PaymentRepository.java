package de.florianbeetz.ma.graphql.payment.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Florian Beetz
 */
public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {

    List<PaymentEntity> findAllByStatusAndOrderUpdatedFalse(String status);

}
