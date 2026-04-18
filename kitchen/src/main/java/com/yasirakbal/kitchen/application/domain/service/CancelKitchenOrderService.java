package com.yasirakbal.kitchen.application.domain.service;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.CancelKitchenOrderUseCase;
import com.yasirakbal.kitchen.application.port.out.LoadKitchenOrderPort;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelKitchenOrderService implements CancelKitchenOrderUseCase {

    private final LoadKitchenOrderPort loadKitchenOrderPort;
    private final SaveKitchenOrderPort saveKitchenOrderPort;

    @Override
    public void cancel(KitchenOrderId kitchenOrderId) {
        KitchenOrder kitchenOrder = loadKitchenOrderPort.getById(kitchenOrderId);
        kitchenOrder.cancel();

        saveKitchenOrderPort.save(kitchenOrder);
    }
}
