package com.yasirakbal.kitchen.application.port.in;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;

public interface CancelKitchenOrderUseCase {
    void cancel(KitchenOrderId kitchenOrderId);
}
