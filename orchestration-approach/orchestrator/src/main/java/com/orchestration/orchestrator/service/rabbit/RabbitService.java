package com.orchestration.orchestrator.service.rabbit;

import com.orchestration.orchestrator.model.dto.FailCommand;
import com.orchestration.orchestrator.model.dto.InventoryCommand;
import com.orchestration.orchestrator.model.dto.OrderCommand;
import com.orchestration.orchestrator.model.dto.PaymentCommand;

public interface RabbitService {

    void sendOrderCommand(OrderCommand orderCommand);

    void sendOrderFailCommand(FailCommand orderFailCommand);

    void sendPaymentCommand(PaymentCommand paymentCommand);

    void sendPaymentFailCommand(FailCommand paymentFailCommand);

    void sendInventoryCommand(InventoryCommand inventoryCommand);

    void sendInventoryFailCommand(FailCommand inventoryFailCommand);
}
