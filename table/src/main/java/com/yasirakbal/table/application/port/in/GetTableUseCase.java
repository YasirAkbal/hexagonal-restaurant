package com.yasirakbal.table.application.port.in;

import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.domain.model.Table;

public interface GetTableUseCase {
    Table getById(TableId tableId);
}

