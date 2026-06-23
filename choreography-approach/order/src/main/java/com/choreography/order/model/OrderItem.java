package com.choreography.order.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "choreography_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "item_code", unique = true, nullable = false)
    private String itemCode;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Column(columnDefinition = "INTEGER CONSTRAINT check_quantity_positive CHECK (quantity >= 0)")
    private Integer quantity;

}
