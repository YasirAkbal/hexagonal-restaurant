package com.yasirakbal.order.application.port.out;

import com.yasirakbal.order.application.domain.model.Order;

public interface CreateOrderPort {
    void createOrder(Order order);
}
