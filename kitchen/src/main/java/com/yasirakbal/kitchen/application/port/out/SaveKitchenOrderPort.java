package com.yasirakbal.kitchen.application.port.out;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;

public interface SaveKitchenOrderPort {
    void save(KitchenOrder kitchenOrder);
}
