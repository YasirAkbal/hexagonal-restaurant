package com.yasirakbal.configuration;

import com.yasirakbal.order.application.port.out.LoadTableStatusPort;
import com.yasirakbal.order.application.port.out.TableInfo;
import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.port.integration.TableInternalService;

public class TableInternalAdapter implements LoadTableStatusPort {

    private final TableInternalService tableInternalService;

    public TableInternalAdapter(TableInternalService tableInternalService) {
        this.tableInternalService = tableInternalService;
    }

    @Override
    public TableInfo getTableInfo(TableId tableId) {
        // 1. Retrieve data from the Table project in a foreign format
        var response = tableInternalService.getTableDetails(tableId);

        // 2. Anti Corruption Layer(ACL)
        // Map the foreign data into your own format(in this toy project, they are in same format)
        return new TableInfo(
                response.tableId(),
                response.status()
        );
    }
}