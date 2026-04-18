package com.yasirakbal.order.application.port.out;

import com.yasirakbal.shared.identifier.TableId;

public interface LoadTableStatusPort {
    TableInfo getTableInfo(TableId tableId);
}
