package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.shared.enums.TableStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class GetTableResultModel {
    UUID tableId;
    Integer tableNumber;
    Integer capacity;
    TableStatus status;
    Integer pendingOrderCount;
}

