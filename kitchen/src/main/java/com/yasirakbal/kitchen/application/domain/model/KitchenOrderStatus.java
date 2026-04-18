package com.yasirakbal.kitchen.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KitchenOrderStatus {
    RECEIVED("RECEIVED"), PREPARING("PREPARING"), READ("READY");

    private final String value;
}
