package com.yasirakbal.kitchen.adapter.out.persistance;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KitchenOrderPersistenceAdapter implements SaveKitchenOrderPort {

    private final KitchenOrderJpaRepository kitchenOrderJpaRepository;

    @Override
    public void save(KitchenOrder kitchenOrder) {

    }
}
