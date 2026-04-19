package com.yasirakbal.table.application.port.integration;

import com.yasirakbal.shared.identifier.TableId;

public interface TableIntegrationPort {
    TableIntegrationResponseDTO getTableDetails(TableId tableId);
}