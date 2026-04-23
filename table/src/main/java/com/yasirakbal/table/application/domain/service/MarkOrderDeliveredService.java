package com.yasirakbal.table.application.domain.service;

import com.yasirakbal.shared.events.TableOrderDeliveredIntegrationEvent;
import com.yasirakbal.shared.identifier.OrderId;
import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.port.in.MarkOrderDeliveredUseCase;
import com.yasirakbal.table.application.port.out.PublishTableOrderDeliveredPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarkOrderDeliveredService implements MarkOrderDeliveredUseCase {

    private final PublishTableOrderDeliveredPort publishTableOrderDeliveredPort;

    @Override
    public void markOrderDelivered(TableId tableId, OrderId orderId) {
        publishTableOrderDeliveredPort.publishOrderDelivered(
                new TableOrderDeliveredIntegrationEvent(orderId.getValue(), tableId.getValue()));
    }
}
