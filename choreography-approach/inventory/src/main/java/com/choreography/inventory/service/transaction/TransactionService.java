package com.choreography.inventory.service.transaction;

import com.choreography.inventory.event.order.OrderCreatedEvent;

public interface TransactionService {

    void addTransactions(OrderCreatedEvent orderCreatedEvent);

}
