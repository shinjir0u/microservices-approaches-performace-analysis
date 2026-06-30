package com.choreography.inventory.event;

import java.util.UUID;

public interface AppEvent {

    String eventId();

    UUID orderId();

}
