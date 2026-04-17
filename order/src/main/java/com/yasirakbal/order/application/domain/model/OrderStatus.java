package com.yasirakbal.order.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING("PENDING"), PREPARING("PREPARING"),
    READY("READY"), DELIVERED("DELIVERED"), CANCELLED("CANCELLED");

    private final String value;
}
