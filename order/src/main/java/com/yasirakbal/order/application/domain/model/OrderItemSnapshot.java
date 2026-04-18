package com.yasirakbal.order.application.domain.model;

import java.util.UUID;

public record OrderItemSnapshot(
        OrderItemId id,
        UUID menuItemId,
        Integer quantity,
        Money price
) {}