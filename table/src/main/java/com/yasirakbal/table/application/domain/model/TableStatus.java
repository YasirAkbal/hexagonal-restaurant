package com.yasirakbal.table.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableStatus {
    OPEN("OPEN"),
    OCCUPIED("OCCUPIED"),
    CLOSED("CLOSED");

    private final String value;
}

