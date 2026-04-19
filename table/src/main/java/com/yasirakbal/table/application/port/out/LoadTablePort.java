package com.yasirakbal.table.application.port.out;

import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.domain.model.Table;

public interface LoadTablePort {
    Table getById(TableId tableId);
}

