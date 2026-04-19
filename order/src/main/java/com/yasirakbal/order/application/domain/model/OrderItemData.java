package com.yasirakbal.order.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.yasirakbal.shared.identifier.MenuItemId;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderItemData {

    private final MenuItemId menuItemId;

    private final Integer quantity;

    private final Money price;

    public static OrderItemData of(MenuItemId menuItemId, Integer quantity, Money price) {
        return new OrderItemData(menuItemId, quantity, price);
    }
}