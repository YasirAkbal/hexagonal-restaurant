package com.yasirakbal.order.application.port.in;


import com.yasirakbal.shared.identifier.OrderId;

public interface CancelOrderUseCase {
    void cancelOrder(OrderId orderId);
}
