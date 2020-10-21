package de.florianbeetz.ma.graphql.shipping.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Florian Beetz
 */
@Repository
public interface ShipmentRepository extends CrudRepository<ShipmentEntity, Long> {

}
