package com.yasirakbal.order.application.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class OrderItem {

    private final OrderItemId id;

    private final String menuItemName;

    private final Integer quantity;

    private final BigDecimal price;

    public static OrderItem withId(OrderItemId id, String menuItemName,
                                   Integer quantity, BigDecimal price) {
        return new OrderItem(id, menuItemName, quantity, price);
    }

    public static OrderItem withoutId(String menuItemName, Integer quantity,
                                      BigDecimal price) {
        return new OrderItem(null, menuItemName, quantity, price);
    }
}
