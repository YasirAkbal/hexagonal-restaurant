package com.yasirakbal.order.application.domain.service;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.UpdateOrderStatusCommand;
import com.yasirakbal.order.application.port.in.UpdateOrderStatusUseCase;
import com.yasirakbal.order.application.port.out.LoadOrderPort;
import com.yasirakbal.order.application.port.out.SaveOrderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateOrderStatusService implements UpdateOrderStatusUseCase {

    private final LoadOrderPort loadOrderPort;
    private final SaveOrderPort saveOrderPort;

    @Override
    public void updateStatus(UpdateOrderStatusCommand command) {
        Order order = loadOrderPort.getById(command.getOrderId());
        order.updateOrderStatus(command.getOrderStatus());

        saveOrderPort.saveOrder(order); //two-way mapping
    }
}
