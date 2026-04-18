package com.yasirakbal.kitchen.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KitchenOrderStatus {
    RECEIVED("RECEIVED"), PREPARING("PREPARING"),
    READY("READY"), CANCELLED("CANCELLED");

    private final String value;
}
