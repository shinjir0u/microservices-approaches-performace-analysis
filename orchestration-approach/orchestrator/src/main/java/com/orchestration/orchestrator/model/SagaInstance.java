package com.orchestration.orchestrator.model;

import com.orchestration.orchestrator.model.type.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "saga_instances")
public class SagaInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sagaId;

    private UUID orderId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean isInventorySuccess;

    private boolean isPaymentSuccess;

    @Version
    private Long version;
    
}
