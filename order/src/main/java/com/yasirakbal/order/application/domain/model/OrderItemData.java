package com.yasirakbal.order.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderItemData {

    private final String menuItemName;
    private final Integer quantity;
    private final Money price;

    public static OrderItemData of(String menuItemName, Integer quantity, Money price) {
        return new OrderItemData(menuItemName, quantity, price);
    }
}