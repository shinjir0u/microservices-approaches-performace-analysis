package com.choreography.order.model.order;

import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.model.order.type.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder(toBuilder = true)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(name = "customer_name")
    private String customerName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    @Version
    private Integer version;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public static Order from(OrderRequest request) {

        String orderNumber = "ORDER_REQ" + Math.random() * 100_000_000;

        List<OrderItem> orderItems = request.orderItemRequests().stream()
                .map(item ->
                        OrderItem.builder().itemCode(item.itemCode()).quantity(item.quantity()).build()
                )
                .toList();

        return Order.builder()
                .number(orderNumber)
                .customerName(request.customerName())
                .status(Status.PENDING)
                .totalAmount(request.totalAmount())
                .orderItems(orderItems)
                .build();
    }

}
