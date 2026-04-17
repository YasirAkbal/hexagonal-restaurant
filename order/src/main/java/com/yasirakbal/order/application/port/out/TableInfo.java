package com.yasirakbal.order.application.port.out;

import enums.TableStatus;
import identifier.TableId;

public record TableInfo(
        TableId id,
        TableStatus status
) {}