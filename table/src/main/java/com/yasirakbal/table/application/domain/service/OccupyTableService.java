package com.yasirakbal.table.application.domain.service;

import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.port.in.OccupyTableUseCase;
import com.yasirakbal.table.application.port.out.LoadTablePort;
import com.yasirakbal.table.application.port.out.SaveTablePort;
import com.yasirakbal.table.common.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OccupyTableService implements OccupyTableUseCase {

    private final LoadTablePort loadTablePort;
    private final SaveTablePort saveTablePort;

    @Override
    public void occupyTable(TableId tableId) {
        Table table = loadTablePort.getById(tableId);
        table.markAsOccupied();

        saveTablePort.save(table);
    }
}
