package com.yasirakbal.order.application.domain.model;

import com.yasirakbal.shared.identifier.MenuItemId;

public record OrderItemSnapshot(
        OrderItemId id,
        MenuItemId menuItemId,
        Integer quantity,
        Money price
) {}