package com.yasirakbal.order.application.port.out;

import com.yasirakbal.shared.events.OrderCancelledIntegrationEvent;

public interface PublishOrderCancelledPort {
    void publishOrderCancelled(OrderCancelledIntegrationEvent event);
}
