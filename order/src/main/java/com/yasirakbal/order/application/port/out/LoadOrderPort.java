package com.yasirakbal.order.application.port.out;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.shared.identifier.OrderId;

public interface LoadOrderPort {
    Order getById(OrderId orderId);
}
