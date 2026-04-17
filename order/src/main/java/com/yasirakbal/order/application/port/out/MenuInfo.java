package com.yasirakbal.order.application.port.out;

import java.math.BigDecimal;
import java.util.UUID;

public record MenuInfo(
        UUID menuId,
        String name,
        BigDecimal price
) {
}
