package com.choreography.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "choreography_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "item_code", unique = true, nullable = false)
    private String itemCode;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Column(columnDefinition = "INTEGER CONSTRAINT check_quantity_positive CHECK (quantity >= 0)")
    private Integer quantity;

}
