package com.yasirakbal.kitchen.application.port.out;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;

public interface LoadKitchenOrderPort {
    KitchenOrder getById(KitchenOrderId kitchenOrderId);
}
