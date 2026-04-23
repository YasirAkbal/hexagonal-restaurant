package com.yasirakbal.order.application.domain.service;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.CancelOrderUseCase;
import com.yasirakbal.order.application.port.out.LoadOrderPort;
import com.yasirakbal.order.application.port.out.PublishOrderCancelledPort;
import com.yasirakbal.order.application.port.out.SaveOrderPort;
import com.yasirakbal.shared.events.OrderCancelledIntegrationEvent;
import com.yasirakbal.shared.identifier.OrderId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelOrderService implements CancelOrderUseCase {

    private final LoadOrderPort loadOrderPort;
    private final SaveOrderPort saveOrderPort;
    private final PublishOrderCancelledPort publishOrderCancelledPort;

    @Override
    public void cancelOrder(OrderId orderId) {
        Order order = loadOrderPort.getById(orderId);
        order.cancelOrder();

        saveOrderPort.saveOrder(order);

        publishOrderCancelledPort.publishOrderCancelled(new OrderCancelledIntegrationEvent(orderId.getValue()));
    }
}
