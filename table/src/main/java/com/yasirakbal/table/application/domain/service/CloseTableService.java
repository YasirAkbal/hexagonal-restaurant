package com.yasirakbal.table.application.domain.service;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;
import com.yasirakbal.table.application.port.in.CloseTableUseCase;
import com.yasirakbal.table.application.port.out.LoadTablePort;
import com.yasirakbal.table.application.port.out.SaveTablePort;
import com.yasirakbal.table.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CloseTableService implements CloseTableUseCase {

    private final LoadTablePort loadTablePort;
    private final SaveTablePort saveTablePort;

    @Override
    public void closeTable(TableId tableId) {
        Table table = loadTablePort.getById(tableId);
        table.close();
        saveTablePort.save(table);
    }
}
