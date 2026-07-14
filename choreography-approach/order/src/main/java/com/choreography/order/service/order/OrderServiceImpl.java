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

    @Transactional
    @Override
    public void processSucceededDomainEvent(UUID orderId) {
        String INVENTORY_RESERVED_EVENT = "InventoryReservedEvent";
        String PAYMENT_CHARGED_EVENT = "PaymentChargedEvent";

        Order order = this.getOrderById(orderId);

        if (order.getStatus() != Status.PENDING) {
            return;
        }

        boolean inventoryReservedEventExists = processedEventRepository.existsByOrderIdAndName(orderId, INVENTORY_RESERVED_EVENT);
        boolean paymentChargedEventExists = processedEventRepository.existsByOrderIdAndName(orderId, PAYMENT_CHARGED_EVENT);

        if (inventoryReservedEventExists && paymentChargedEventExists) {
            order.setStatus(Status.SUCCESS);
            orderRepository.save(order);
            log.info("Update status of order with id: {} to SUCCESS", orderId);
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
