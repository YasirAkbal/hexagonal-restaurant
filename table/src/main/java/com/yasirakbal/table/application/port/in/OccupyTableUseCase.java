package com.yasirakbal.table.application.port.in;

import com.yasirakbal.shared.identifier.TableId;

public interface OccupyTableUseCase {
    void occupyTable(TableId tableId);
}

