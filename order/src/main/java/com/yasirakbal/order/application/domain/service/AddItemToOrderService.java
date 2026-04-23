package com.yasirakbal.order.application.domain.service;

import com.yasirakbal.order.application.domain.model.Money;
import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.domain.model.OrderItemData;
import com.yasirakbal.order.application.port.in.AddItemToOrderCommand;
import com.yasirakbal.order.application.port.in.AddItemToOrderUseCase;
import com.yasirakbal.order.application.port.out.*;
import com.yasirakbal.shared.identifier.OrderId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddItemToOrderService implements AddItemToOrderUseCase {

    private final LoadMenuInfoPort loadMenuInfoPort;
    private final SaveOrderPort saveOrderPort;
    private final LoadOrderPort loadOrderPort;


    @Override
    public Order addItem(AddItemToOrderCommand command) {
        OrderId orderId = command.getOrderId();
        Order order = loadOrderPort.getById(orderId);

        MenuInfo menuInfo = loadMenuInfoPort.getMenuInfo(command.getMenuItemId());
        order.addItem(OrderItemData.of(
                menuInfo.menuId(),
                command.getQuantity(),
                Money.of(menuInfo.price())
        ));

        saveOrderPort.saveOrder(order);
        return order;
    }
}
