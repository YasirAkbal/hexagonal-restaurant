package com.yasirakbal.kitchen.application.port.in;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;

public interface StartPreparingUseCase {
    void startPreparing(KitchenOrderId kitchenOrderId);
}
