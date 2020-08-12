package de.florianbeetz.ma.graphql.inventory.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemStockEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ItemEntity item;

    @ManyToOne
    private WarehouseEntity warehouse;

    @Column(nullable = false)
    private long inStock;

    @Column(nullable = false)
    private long available;

}
