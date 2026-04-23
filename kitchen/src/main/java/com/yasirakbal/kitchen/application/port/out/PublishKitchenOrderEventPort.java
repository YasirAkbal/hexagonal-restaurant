package com.yasirakbal.kitchen.application.port.out;

import com.yasirakbal.shared.events.KitchenOrderReadyIntegrationEvent;

public interface PublishKitchenOrderEventPort {

    void publishKitchenOrderReady(KitchenOrderReadyIntegrationEvent event);
}
