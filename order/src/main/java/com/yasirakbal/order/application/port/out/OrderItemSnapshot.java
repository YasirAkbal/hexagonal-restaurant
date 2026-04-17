package com.yasirakbal.order.application.port.out;

import com.yasirakbal.order.application.domain.model.Money;
import com.yasirakbal.order.application.domain.model.OrderItemId;

import java.util.UUID;

public record OrderItemSnapshot(
        OrderItemId id,
        UUID menuItemId,
        Integer quantity,
        Money price
) {}