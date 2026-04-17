package com.yasirakbal.order.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class OrderItem {

    private final OrderItemId id;

    private final UUID menuItemId;

    private final Integer quantity;

    private final Money price;

    public static OrderItem withId(OrderItemId id, UUID menuItemId,
                                   Integer quantity, Money price) {
        return new OrderItem(id, menuItemId, quantity, price);
    }

    public static OrderItem withoutId(UUID menuItemId, Integer quantity,
                                      Money price) {
        return new OrderItem(null, menuItemId, quantity, price);
    }
}
