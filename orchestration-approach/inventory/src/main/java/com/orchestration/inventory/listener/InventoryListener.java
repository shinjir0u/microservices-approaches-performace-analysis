package com.orchestration.inventory.listener;

import com.orchestration.inventory.config.RabbitMQSetting;
import com.orchestration.inventory.model.command.InventoryCommand;
import com.orchestration.inventory.service.rabbit.RabbitService;
import com.orchestration.inventory.usecase.ReserveInventoryUseCase;
import com.orchestration.inventory.usecase.RevertInventoryTransactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryListener {

    private final ReserveInventoryUseCase reserveInventoryUseCase;

    private final RevertInventoryTransactionUseCase revertInventoryTransactionUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = RabbitMQSetting.INVENTORY_COMMAND_QUEUE)
    public void handleInventoryReservedCommand(InventoryCommand inventoryCommand) {
        log.info("Received inventoryCommand with order id: {}", inventoryCommand.orderId());

        try {
            reserveInventoryUseCase.execute(inventoryCommand);
            rabbitService.sendSagaReplyWithStatus(inventoryCommand.sagaId(), inventoryCommand.orderId(), true);
        } catch (IllegalArgumentException _) {
            rabbitService.sendSagaReplyWithStatus(inventoryCommand.sagaId(), inventoryCommand.orderId(), false);
        }
    }

    @RabbitListener(queues = RabbitMQSetting.INVENTORY_FAIL_COMMAND_QUEUE)
    public void handleInventoryFailCommand(InventoryCommand inventoryCommand) {
        log.info("Received inventoryFailCommand with order id: {}", inventoryCommand.orderId());

        revertInventoryTransactionUseCase.execute(inventoryCommand);
        rabbitService.sendSagaReplyWithStatus(inventoryCommand.sagaId(), inventoryCommand.orderId(), true);
    }

}
