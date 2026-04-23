package com.yasirakbal.order.application.port.out;

import com.yasirakbal.shared.events.OrderPlacedIntegrationEvent;

public interface PublishOrderPlacedPort {
    void publishOrderPlaced(OrderPlacedIntegrationEvent event);
}
