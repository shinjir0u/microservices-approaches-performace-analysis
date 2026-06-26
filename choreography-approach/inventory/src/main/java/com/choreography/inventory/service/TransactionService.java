package com.choreography.inventory.service;

import com.choreography.inventory.events.order.OrderCreatedEvent;

public interface TransactionService {

    void addTransactions(OrderCreatedEvent orderCreatedEvent);

}
