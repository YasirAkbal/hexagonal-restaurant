package com.yasirakbal.order.application.port.out;

import com.yasirakbal.shared.identifier.MenuItemId;

import java.math.BigDecimal;

public record MenuInfo(
        MenuItemId menuId,
        String name,
        BigDecimal price
) {
}
