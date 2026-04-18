package com.yasirakbal.kitchen.application.domain.service;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.StartPreparingUseCase;
import com.yasirakbal.kitchen.application.port.out.LoadKitchenOrderPort;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StartPreparingService implements StartPreparingUseCase {

    private final LoadKitchenOrderPort loadKitchenOrderPort;
    private final SaveKitchenOrderPort saveKitchenOrderPort;

    @Override
    public void startPreparing(KitchenOrderId kitchenOrderId) {
        KitchenOrder kitchenOrder = loadKitchenOrderPort.getById(kitchenOrderId);
        kitchenOrder.startPreparing();

        saveKitchenOrderPort.save(kitchenOrder);
    }
}
