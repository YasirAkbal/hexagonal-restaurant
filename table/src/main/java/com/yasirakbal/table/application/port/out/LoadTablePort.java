package com.yasirakbal.table.application.port.out;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;

public interface LoadTablePort {
    Table getById(TableId tableId);
}

