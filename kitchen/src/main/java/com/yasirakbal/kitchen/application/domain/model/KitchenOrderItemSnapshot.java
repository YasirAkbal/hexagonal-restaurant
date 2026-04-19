package com.yasirakbal.kitchen.application.domain.model;

import com.yasirakbal.shared.identifier.MenuItemId;

public record KitchenOrderItemSnapshot(
        KitchenOrderItemId id,
        KitchenOrderId kitchenOrderId,
        MenuItemId menuItemId,
        Integer quantity
) {
}
