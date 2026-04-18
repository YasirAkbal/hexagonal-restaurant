package com.yasirakbal.kitchen.application.domain.model;

import java.util.UUID;

public record KitchenOrderItemSnapshot(
        KitchenOrderItemId id,
        KitchenOrderId kitchenOrderId,
        MenuItemId menuItemId,
        Integer quantity
) {
}
