package com.yasirakbal.kitchen.application.port.in;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.shared.identifier.OrderId;

public interface CancelKitchenOrderUseCase {
    void cancel(KitchenOrderId kitchenOrderId);

    void cancelByOrderId(OrderId orderId);
}
