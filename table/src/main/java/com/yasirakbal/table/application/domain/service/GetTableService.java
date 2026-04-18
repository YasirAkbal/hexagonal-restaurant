package com.yasirakbal.table.application.domain.service;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;
import com.yasirakbal.table.application.port.in.GetTableUseCase;
import com.yasirakbal.table.application.port.out.LoadTablePort;
import com.yasirakbal.table.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetTableService implements GetTableUseCase {

    private final LoadTablePort loadTablePort;

    @Override
    public Table getTable(TableId tableId) {
        return loadTablePort.getById(tableId);
    }
}

