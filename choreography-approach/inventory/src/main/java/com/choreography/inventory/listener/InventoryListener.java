package com.choreography.inventory.listener;

import com.choreography.inventory.events.order.OrderCreatedEvent;
import com.choreography.inventory.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryListener {

    private final TransactionService transactionService;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.inventory.queue}")
    public void receiveOrder(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());

        transactionService.addTransactions(orderCreatedEvent);
    }

}
