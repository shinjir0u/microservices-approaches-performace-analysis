package com.choreography.inventory.service;

import com.choreography.inventory.events.inventory.InventoryReservedEvent;
import com.choreography.inventory.events.order.OrderCreatedEvent;
import com.choreography.inventory.model.inventory.Item;
import com.choreography.inventory.model.inventory.Transaction;
import com.choreography.inventory.model.inventory.type.TransactionStatus;
import com.choreography.inventory.repository.ItemRepository;
import com.choreography.inventory.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Value("${spring.rabbitmq.inventory.reserved.exchange}")
    private String inventoryReservedExchange;

    @Value("${spring.rabbitmq.inventory.reserved.routingKey}")
    private String inventoryReservedRoutingKey;

    private final ItemRepository itemRepository;

    private final TransactionRepository transactionRepository;

    private final RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    public void addTransactions(OrderCreatedEvent orderCreatedEvent) {

        List<Transaction> transactions = orderCreatedEvent.items().stream()
                .map(
                        orderedItem -> {
                            Item inventoryItem = itemRepository.findByItemCode(orderedItem.itemCode()).orElseThrow(IllegalArgumentException::new);
                            var updatedItem = inventoryItem.subtractQuantity(orderedItem.quantity());

                            return Transaction.builder()
                                    .orderId(orderCreatedEvent.orderId())
                                    .item(updatedItem)
                                    .quantity(orderedItem.quantity())
                                    .status(TransactionStatus.SUCCESS)
                                    .build();
                        }
                ).toList();

        transactions.forEach(transaction -> {
            transactionRepository.save(transaction);
            log.info("Created transaction with id: {}", transaction.getId());
        });

        InventoryReservedEvent inventoryReservedEvent = InventoryReservedEvent
                .builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(orderCreatedEvent.orderId())
                .build();

        rabbitTemplate.convertAndSend(inventoryReservedExchange, inventoryReservedRoutingKey, inventoryReservedEvent);
        log.info("Published inventoryReservedEvent with id: {}", inventoryReservedEvent.eventId());
    }

}
