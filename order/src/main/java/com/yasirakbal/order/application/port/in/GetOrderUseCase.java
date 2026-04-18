package com.yasirakbal.order.application.port.in;


import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.domain.model.OrderId;

public interface GetOrderUseCase {
    Order getOrder(OrderId orderId);
}
