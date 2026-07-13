package com.choreography.inventory.service.transaction;

import com.choreography.inventory.event.order.OrderCreatedEvent;
import com.choreography.inventory.event.payment.PaymentFailedEvent;

public interface TransactionService {

    void addTransactions(OrderCreatedEvent orderCreatedEvent);

    void revertTransactions(PaymentFailedEvent paymentFailedEvent);

}
