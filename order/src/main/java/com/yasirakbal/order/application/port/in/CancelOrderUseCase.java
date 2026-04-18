package com.yasirakbal.order.application.port.in;

import com.yasirakbal.order.application.domain.model.OrderId;

public interface CancelOrderUseCase {
    void cancelOrder(OrderId orderId);
}
