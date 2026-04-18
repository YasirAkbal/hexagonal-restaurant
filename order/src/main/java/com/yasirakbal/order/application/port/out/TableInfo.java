package com.yasirakbal.order.application.port.out;


import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.TableId;

public record TableInfo(
        TableId id,
        TableStatus status
) {}