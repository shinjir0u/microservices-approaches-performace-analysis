package com.choreography.inventory.service.transaction;

import com.choreography.inventory.event.order.OrderCreatedEvent;
import com.choreography.inventory.event.payment.PaymentFailedEvent;
import com.choreography.inventory.model.inventory.Item;
import com.choreography.inventory.model.inventory.Transaction;
import com.choreography.inventory.model.inventory.type.TransactionStatus;
import com.choreography.inventory.repository.ItemRepository;
import com.choreography.inventory.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ItemRepository itemRepository;

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void addTransactions(OrderCreatedEvent orderCreatedEvent) {

        orderCreatedEvent.items()
                .forEach(
                        orderedItem -> {
                            Item inventoryItem = itemRepository.findByItemCode(orderedItem.itemCode()).orElseThrow(IllegalArgumentException::new);
                            var updatedItem = inventoryItem.subtractQuantity(orderedItem.quantity());

                            var transaction = Transaction.builder()
                                    .orderId(orderCreatedEvent.orderId())
                                    .item(updatedItem)
                                    .quantity(orderedItem.quantity())
                                    .status(TransactionStatus.SUCCESS)
                                    .build();

                            Transaction savedTransaction = transactionRepository.save(transaction);
                            log.info("Created transaction with id: {}", savedTransaction.getId());
                        }
                );

    }

    @Override
    public void revertTransactions(PaymentFailedEvent paymentFailedEvent) {
        List<Transaction> transactions = transactionRepository.findAllByOrderId(paymentFailedEvent.orderId());

        transactions
                .forEach(
                        transaction -> {
                            Item updatedItem = transaction.getItem().addQuantity(transaction.getQuantity());
                            Transaction updatedTransaction = transaction.toBuilder().item(updatedItem).status(TransactionStatus.FAIL).build();
                            transactionRepository.save(updatedTransaction);
                            log.info("Reverted transaction with id: {}", updatedTransaction.getId());
                        }
                );
    }

}
