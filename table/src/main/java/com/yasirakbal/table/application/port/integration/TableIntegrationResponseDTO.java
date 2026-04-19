package com.yasirakbal.table.application.port.integration;

import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.TableId;

public record TableIntegrationResponseDTO(TableId tableId, TableStatus status) {}