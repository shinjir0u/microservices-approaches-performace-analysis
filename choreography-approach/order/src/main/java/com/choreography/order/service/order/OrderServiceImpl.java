package com.choreography.order.service.order;

import com.choreography.order.model.order.Order;
import com.choreography.order.model.order.type.Status;
import com.choreography.order.repository.OrderRepository;
import com.choreography.order.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProcessedEventRepository processedEventRepository;

    @Override
    public void updateValidSucceededOrderStatus(UUID orderId) {
        String INVENTORY_RESERVED_EVENT = "InventoryReservedEvent";
        String PAYMENT_CHARGED_EVENT = "PaymentChargedEvent";

        Order order = getOrderById(orderId);

        if (order.getStatus() == Status.SUCCESS || order.getStatus() == Status.FAILED) {
            log.info("Order with id: {} has already been handled", orderId);
            return;
        }

        if (order.getStatus() != Status.PENDING)
            return;

        boolean inventoryReservedEventExists = processedEventRepository.existsByOrderIdAndName(orderId, INVENTORY_RESERVED_EVENT);
        boolean paymentChargedEventExists = processedEventRepository.existsByOrderIdAndName(orderId, PAYMENT_CHARGED_EVENT);

        log.info("Inventory reserved {}", inventoryReservedEventExists);
        log.info("Payment charged {}", paymentChargedEventExists);

        if (inventoryReservedEventExists && paymentChargedEventExists) {
            order.setStatus(Status.SUCCESS);
            orderRepository.save(order);
            log.info("Update status of order with id: {} to SUCCESS", orderId);
        }
    }

    @Override
    public void processFailedDomainEvent(UUID orderId, String domainEventName) {
        String INVENTORY_FAILED_EVENT = "InventoryFailedEvent";
        String PAYMENT_FAILED_EVENT = "PaymentFailedEvent";

        Order order = getOrderById(orderId);
        if (order.getStatus() != Status.PENDING)
            return;

        if (INVENTORY_FAILED_EVENT.equals(domainEventName) || PAYMENT_FAILED_EVENT.equals(domainEventName)) {
            order.setStatus(Status.FAILED);
            orderRepository.save(order);
            log.info("Update status of order with id: {} to FAILED", orderId);
        }
    }

    @Override
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        log.info("Created order with id: {}", savedOrder.getId());
    }

}
