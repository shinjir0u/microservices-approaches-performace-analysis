package com.choreography.order.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "item_code", nullable = false)
    private String itemCode;

    @Column(columnDefinition = "INTEGER CONSTRAINT check_quantity_positive CHECK (quantity >= 0)")
    private Integer quantity;

}
