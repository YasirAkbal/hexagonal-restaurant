package com.yasirakbal.order.application.port.out;

import identifier.TableId;

public interface LoadTableStatusPort {
    TableInfo getTableInfo(TableId tableId);
}
