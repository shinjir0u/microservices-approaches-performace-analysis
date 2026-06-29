package com.choreography.inventory.service.transaction;

import com.choreography.inventory.events.order.OrderCreatedEvent;

public interface TransactionService {

    void addTransactions(OrderCreatedEvent orderCreatedEvent);

}
