package com.yasirakbal.order.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderItemData {

    private final UUID menuItemId;

    private final Integer quantity;

    private final BigDecimal price;

    public static OrderItemData of(UUID menuItemId, Integer quantity, BigDecimal price) {
        return new OrderItemData(menuItemId, quantity, price);
    }
}