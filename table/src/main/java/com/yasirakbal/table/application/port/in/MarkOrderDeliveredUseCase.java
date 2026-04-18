package com.yasirakbal.table.application.port.in;

import com.yasirakbal.table.application.domain.model.TableId;

public interface MarkOrderDeliveredUseCase {
    void markOrderDelivered(TableId tableId);
}

