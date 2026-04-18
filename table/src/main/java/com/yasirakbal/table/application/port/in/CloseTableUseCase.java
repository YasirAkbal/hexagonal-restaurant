package com.yasirakbal.table.application.port.in;

import com.yasirakbal.table.application.domain.model.TableId;

public interface CloseTableUseCase {
    void closeTable(TableId tableId);
}

