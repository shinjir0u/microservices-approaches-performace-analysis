package com.orchestration.orchestrator.service.rabbit;

import com.orchestration.orchestrator.model.dto.InventoryCommand;
import com.orchestration.orchestrator.model.dto.OrderCommand;
import com.orchestration.orchestrator.model.dto.PaymentCommand;

public interface RabbitService {

    void sendOrderCommand(OrderCommand orderCommand);

    void sendPaymentCommand(PaymentCommand paymentCommand);

    void sendInventoryCommand(InventoryCommand inventoryCommand);
}
