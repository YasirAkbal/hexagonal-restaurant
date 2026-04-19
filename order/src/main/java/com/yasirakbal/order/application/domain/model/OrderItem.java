package com.yasirakbal.order.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.yasirakbal.shared.identifier.MenuItemId;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class OrderItem {

    private OrderItemId id;

    private MenuItemId menuItemId;

    @Setter
    private Integer quantity;

    private Money price;

    public static OrderItem withId(OrderItemId id, MenuItemId menuItemId,
                                   Integer quantity, Money price) {
        return new OrderItem(id, menuItemId, quantity, price);
    }

    public static OrderItem withoutId(MenuItemId menuItemId, Integer quantity,
                                      Money price) {
        return new OrderItem(null, menuItemId, quantity, price);
    }
}
