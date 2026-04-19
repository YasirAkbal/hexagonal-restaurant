package com.yasirakbal.order.application.port.in;

import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.OrderId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class AddItemToOrderCommand {

    @NotNull
    OrderId orderId;

    @NotNull
    MenuItemId menuItemId;

    @NotNull
    @Positive
    Integer quantity;

    public static AddItemToOrderCommand of(OrderId orderId, MenuItemId menuItemId, Integer quantity) {
        return new AddItemToOrderCommand(orderId, menuItemId, quantity);
    }
}