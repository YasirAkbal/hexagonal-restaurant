package com.yasirakbal.order.application.port.out;

import enums.TableStatus;
import identifier.TableId;

public interface RetrieveTablePort {
    TableInfo getTableInfo(TableId tableId);
}
