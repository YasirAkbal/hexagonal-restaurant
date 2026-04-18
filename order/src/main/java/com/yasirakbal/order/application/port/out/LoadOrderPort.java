package com.yasirakbal.order.application.port.out;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.domain.model.OrderId;

public interface LoadOrderPort {
    Order getById(OrderId orderId);
}
