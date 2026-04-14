package com.yasirakbal.order.application.domain.service;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.PlaceOrderCommand;
import com.yasirakbal.order.application.port.in.PlaceOrderUseCase;

public class PlaceOrderService implements PlaceOrderUseCase {
    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        return null;
    }
}
