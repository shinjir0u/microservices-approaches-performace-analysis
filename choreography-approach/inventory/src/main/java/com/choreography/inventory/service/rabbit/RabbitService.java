package com.choreography.inventory.service.rabbit;

import java.util.UUID;

public interface RabbitService {

    void publishInventoryReservedEvent(UUID orderId);

}
