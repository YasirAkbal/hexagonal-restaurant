package com.yasirakbal.table.application.port.in;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;

public interface OpenTableUseCase {
    Table openTable(TableId tableId);
}

