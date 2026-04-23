package com.yasirakbal.table.application.port.in;

import com.yasirakbal.shared.identifier.OrderId;
import com.yasirakbal.shared.identifier.TableId;

public interface MarkOrderDeliveredUseCase {

    void markOrderDelivered(TableId tableId, OrderId orderId);
}
