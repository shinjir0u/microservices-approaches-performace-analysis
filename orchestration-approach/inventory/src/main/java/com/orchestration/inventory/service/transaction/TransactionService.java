package com.orchestration.inventory.service.transaction;

import com.orchestration.inventory.model.command.InventoryCommand;

public interface TransactionService {

    void addTransactions(InventoryCommand inventoryCommand);

    void revertTransactions(InventoryCommand inventoryCommand);

}
