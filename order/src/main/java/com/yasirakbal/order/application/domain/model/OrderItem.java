package com.yasirakbal.order.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class OrderItem {

    private final OrderItemId id;

    private final String menuItemName;

    private final Integer quantity;

    private final Money price;

    public static OrderItem withId(OrderItemId id, String menuItemName,
                                   Integer quantity, Money price) {
        return new OrderItem(id, menuItemName, quantity, price);
    }

    public static OrderItem withoutId(String menuItemName, Integer quantity,
                                      Money price) {
        return new OrderItem(null, menuItemName, quantity, price);
    }
}
