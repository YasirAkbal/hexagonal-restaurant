package com.yasirakbal.kitchen.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.yasirakbal.shared.identifier.MenuItemId;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KitchenOrderItemData {

    private MenuItemId menuItemId;

    private Integer quantity;

    public static KitchenOrderItemData of(MenuItemId menuItemId, Integer quantity) {
        return new KitchenOrderItemData(menuItemId, quantity);
    }
}
