package com.yasirakbal.table.application.port.integration;

import com.yasirakbal.shared.identifier.TableId;

public interface TableInternalService {
    TableResponseDTO getTableDetails(TableId tableId);
}