package com.choreography.payment.service.rabbit;

import java.util.UUID;

public interface RabbitService {

    void publishPaymentChargedEvent(UUID paymentId, UUID orderId);

}
