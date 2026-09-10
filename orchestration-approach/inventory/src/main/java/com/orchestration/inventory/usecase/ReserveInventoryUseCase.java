package com.orchestration.inventory.usecase;

import com.orchestration.inventory.model.command.InventoryCommand;
import com.orchestration.inventory.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReserveInventoryUseCase {

    private final TransactionService transactionService;

    public void execute(InventoryCommand inventoryCommand) {
        transactionService.addTransactions(inventoryCommand);
    }

}
