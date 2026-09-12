package com.orchestration.orchestrator.model;

import com.orchestration.orchestrator.model.dto.SagaStartCommand;
import com.orchestration.orchestrator.model.type.InventoryStatus;
import com.orchestration.orchestrator.model.type.PaymentStatus;
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

    private InventoryStatus inventoryStatus;

    private PaymentStatus paymentStatus;

    @Version
    private Long version;

    public static SagaInstance from(SagaStartCommand sagaStartCommand) {
        return SagaInstance.builder()
                .sagaId(UUID.randomUUID())
                .orderId(sagaStartCommand.orderId())
                .status(Status.PROCESSING)
                .inventoryStatus(InventoryStatus.PENDING)
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

}
