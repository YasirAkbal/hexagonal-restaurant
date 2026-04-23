package com.yasirakbal.table.application.port.out;

import com.yasirakbal.shared.events.TableOrderDeliveredIntegrationEvent;

public interface PublishTableOrderDeliveredPort {

    void publishOrderDelivered(TableOrderDeliveredIntegrationEvent event);
}
