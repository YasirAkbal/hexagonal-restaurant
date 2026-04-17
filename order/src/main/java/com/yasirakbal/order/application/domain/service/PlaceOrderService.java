package com.yasirakbal.order.application.domain.service;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.domain.model.OrderItemData;
import com.yasirakbal.order.application.port.in.PlaceOrderCommand;
import com.yasirakbal.order.application.port.in.PlaceOrderUseCase;
import com.yasirakbal.order.application.port.out.*;
import identifier.TableId;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final CreateOrderPort createOrderPort;
    private final RetrieveTablePort retrieveTablePort;
    private final RetrieveMenuPort retrieveMenuPort;

    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        TableId tableId = command.getTableId();
        TableInfo tableInfo = retrieveTablePort.getTableInfo(tableId);
        List<OrderItemData> orderItems = command.getOrderItems().stream()
                .map(o -> {
                    MenuInfo menuInfo = retrieveMenuPort.getMenuInfo(o.getMenuId());
                    return OrderItemData.of(menuInfo.menuId(), o.getQuantity(), menuInfo.price());
                })
                .toList();

        Order order = Order.placeOrder(tableId, tableInfo.status(), orderItems);

        createOrderPort.createOrder(order);

        return order;
    }
}
