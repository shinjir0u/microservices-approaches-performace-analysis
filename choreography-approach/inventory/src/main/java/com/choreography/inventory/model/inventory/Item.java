package com.choreography.inventory.model.inventory;

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
@Table(name = "inventory_items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "item_code")
    private String itemCode;

    private Integer quantity;

    private BigDecimal amount;

    public Item addQuantity(Integer quantity) {
        this.quantity += quantity;
        return this;
    }

    public Item subtractQuantity(Integer quantity) {
        this.quantity -= quantity;
        return this;
    }

}
