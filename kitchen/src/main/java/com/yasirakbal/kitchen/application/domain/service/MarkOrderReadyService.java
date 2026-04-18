package com.yasirakbal.kitchen.application.domain.service;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.MarkOrderReadyUseCase;
import com.yasirakbal.kitchen.application.port.out.LoadKitchenOrderPort;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarkOrderReadyService implements MarkOrderReadyUseCase {

    private final LoadKitchenOrderPort loadKitchenOrderPort;
    private final SaveKitchenOrderPort saveKitchenOrderPort;

    @Override
    public void markOrderReady(KitchenOrderId kitchenOrderId) {
        KitchenOrder kitchenOrder = loadKitchenOrderPort.getById(kitchenOrderId);
        kitchenOrder.markReady();

        saveKitchenOrderPort.save(kitchenOrder);
    }
}
