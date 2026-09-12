package com.orchestration.orchestrator.model;

import com.orchestration.orchestrator.model.dto.SagaStartCommand;
import com.orchestration.orchestrator.model.type.ServiceStatus;
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

    @Enumerated(EnumType.STRING)
    private ServiceStatus inventoryStatus;

    @Enumerated(EnumType.STRING)
    private ServiceStatus paymentStatus;

    @Version
    private Long version;

    public static SagaInstance from(SagaStartCommand sagaStartCommand) {
        return SagaInstance.builder()
                .sagaId(UUID.randomUUID())
                .orderId(sagaStartCommand.orderId())
                .status(Status.PROCESSING)
                .inventoryStatus(ServiceStatus.PENDING)
                .paymentStatus(ServiceStatus.PENDING)
                .build();
    }

    public void updateInventoryStatus(boolean success) {
        setInventoryStatus(success ? ServiceStatus.SUCCESS : ServiceStatus.FAIL);
    }

    public void updatePaymentStatus(boolean success) {
        setPaymentStatus(success ? ServiceStatus.SUCCESS : ServiceStatus.FAIL);
    }

    public boolean isAllServicesSucceeded() {
        return ServiceStatus.SUCCESS.equals(inventoryStatus) && ServiceStatus.SUCCESS.equals(paymentStatus);
    }

    public boolean isInventoryFailed() {
        return ServiceStatus.FAIL.equals(getInventoryStatus());
    }

    public boolean isPaymentFailed() {
        return ServiceStatus.FAIL.equals(getPaymentStatus());
    }

}
