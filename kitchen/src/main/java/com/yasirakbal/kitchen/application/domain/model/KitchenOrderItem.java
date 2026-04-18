package com.yasirakbal.kitchen.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class KitchenOrderItem {

    private KitchenOrderItemId id;

    private KitchenOrderId kitchenOrderId;

    private MenuItemId menuItemId;

    private Integer quantity;

    public static KitchenOrderItem of(KitchenOrderItemId id, KitchenOrderId kitchenOrderId,
                                      MenuItemId menuItemId, Integer quantity) {
        return new KitchenOrderItem(id, kitchenOrderId, menuItemId, quantity);
    }
}
