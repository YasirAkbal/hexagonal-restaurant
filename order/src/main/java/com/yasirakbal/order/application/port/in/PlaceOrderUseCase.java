package com.yasirakbal.order.application.port.in;

import com.yasirakbal.order.application.domain.model.Order;

public interface PlaceOrderUseCase {
    Order placeOrder(PlaceOrderCommand command);
}
