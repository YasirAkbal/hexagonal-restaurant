package com.yasirakbal.order.application.domain.service;

import com.yasirakbal.order.application.domain.model.Money;
import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.domain.model.OrderItemData;
import com.yasirakbal.order.application.port.in.PlaceOrderCommand;
import com.yasirakbal.order.application.port.in.PlaceOrderUseCase;
import com.yasirakbal.order.application.port.out.*;
import com.yasirakbal.shared.events.OrderPlacedIntegrationEvent;
import com.yasirakbal.shared.events.OrderPlacedLineItem;
import com.yasirakbal.shared.identifier.TableId;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final SaveOrderPort saveOrderPort;
    private final LoadTableStatusPort loadTableStatusPort;
    private final LoadMenuInfoPort loadMenuInfoPort;
    private final PublishOrderPlacedPort publishOrderPlacedPort;

    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        TableId tableId = command.getTableId();
        TableInfo tableInfo = loadTableStatusPort.getTableInfo(tableId);
        List<OrderItemData> orderItems = command.getOrderItems().stream()
                .map(o -> {
                    MenuInfo menuInfo = loadMenuInfoPort.getMenuInfo(o.getMenuItemId());
                    return OrderItemData.of(menuInfo.menuId(), o.getQuantity(), Money.of(menuInfo.price()));
                })
                .toList();

        Order order = Order.placeOrder(tableId, tableInfo.status(), orderItems);

        saveOrderPort.saveOrder(order);

        List<OrderPlacedLineItem> lineItems = order.getItemSnapshots().stream()
                .map(s -> new OrderPlacedLineItem(s.menuItemId().getValue(), s.quantity()))
                .toList();
        publishOrderPlacedPort.publishOrderPlaced(new OrderPlacedIntegrationEvent(
                order.getId().getValue(),
                order.getTableId().getValue(),
                lineItems));

        return order;
    }
}
