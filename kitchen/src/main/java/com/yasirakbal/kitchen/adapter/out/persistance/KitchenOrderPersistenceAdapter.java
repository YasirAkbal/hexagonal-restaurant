package com.yasirakbal.kitchen.adapter.out.persistance;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.GetKitchenOrderUseCase;
import com.yasirakbal.kitchen.application.port.out.LoadKitchenOrderPort;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//I took shortcut for a simple data fetching use case(GetKitchenOrderUseCase) again
public class KitchenOrderPersistenceAdapter implements SaveKitchenOrderPort, LoadKitchenOrderPort, GetKitchenOrderUseCase {

    private final KitchenOrderJpaRepository kitchenOrderJpaRepository;
    private final KitchenOrderMapper kitchenOrderMapper;

    @Override
    public void save(KitchenOrder kitchenOrder) {
        KitchenOrderJpaEntity kitchenOrderJpaEntity = kitchenOrderMapper.mapToJpaEntity(kitchenOrder);
        kitchenOrderJpaRepository.save(kitchenOrderJpaEntity);
    }

    @Override
    public KitchenOrder getById(KitchenOrderId kitchenOrderId) {
        KitchenOrderJpaEntity jpaEntity = kitchenOrderJpaRepository.findById(kitchenOrderId.getValue())
                .orElseThrow(() ->
                        new IllegalArgumentException("Kitchen Order is not found for id = %s"
                                .formatted(kitchenOrderId.getValue()))
                );

        return kitchenOrderMapper.mapToKitchenOrder(jpaEntity);
    }
}
