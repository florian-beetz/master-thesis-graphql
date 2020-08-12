package de.florianbeetz.ma.graphql.inventory.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<ItemEntity, Long> {
}
