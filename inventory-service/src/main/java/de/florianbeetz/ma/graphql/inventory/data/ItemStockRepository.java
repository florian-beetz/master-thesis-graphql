package de.florianbeetz.ma.graphql.inventory.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStockRepository extends PagingAndSortingRepository<ItemStockEntity, Long> {

    @Query("select i from ItemStockEntity i where i.item.id = :id")
    Page<ItemStockEntity> findAllByItemId(@Param("id") long id, Pageable page);

    @Query("select i from ItemStockEntity i where i.warehouse.id = :id")
    Page<ItemStockEntity> findAllByWarehouseId(@Param("id") long id, Pageable page);

    @Query("select coalesce(sum(i.available), 0) from ItemStockEntity i where i.item.id = :id")
    long totalAvailableByItemId(@Param("id") long id);

    @Query("select coalesce(sum(i.inStock), 0) from ItemStockEntity i where i.item.id = :id")
    long totalInStockByItemId(@Param("id") long id);
}
