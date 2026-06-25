package com.choreography.payment.model.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(columnDefinition = "NUMERIC(38,2) CONSTRAINT check_positive_amount CHECK (amount >= 0)")
    private BigDecimal amount;

    @Column(name = "paid_at")
    private Instant paidAt;

}
