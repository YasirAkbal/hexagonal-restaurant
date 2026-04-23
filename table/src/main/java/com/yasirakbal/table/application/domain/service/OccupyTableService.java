package com.yasirakbal.table.application.domain.service;

import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.port.in.OccupyTableUseCase;
import com.yasirakbal.table.application.port.out.LoadTablePort;
import com.yasirakbal.table.application.port.out.SaveTablePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OccupyTableService implements OccupyTableUseCase {

    private final LoadTablePort loadTablePort;
    private final SaveTablePort saveTablePort;

    @Override
    public void occupyTable(TableId tableId) {
        Table table = loadTablePort.getById(tableId);
        if (table.getStatus().equals(TableStatus.OCCUPIED)) {
            return;
        }
        table.markAsOccupied();

        saveTablePort.save(table);
    }
}
