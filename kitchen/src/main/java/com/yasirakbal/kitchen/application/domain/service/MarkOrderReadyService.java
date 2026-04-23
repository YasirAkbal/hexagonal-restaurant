package com.yasirakbal.kitchen.application.domain.service;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.MarkOrderReadyUseCase;
import com.yasirakbal.kitchen.application.port.out.LoadKitchenOrderPort;
import com.yasirakbal.kitchen.application.port.out.PublishKitchenOrderEventPort;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import com.yasirakbal.shared.events.KitchenOrderReadyIntegrationEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarkOrderReadyService implements MarkOrderReadyUseCase {

    private final LoadKitchenOrderPort loadKitchenOrderPort;
    private final SaveKitchenOrderPort saveKitchenOrderPort;
    private final PublishKitchenOrderEventPort publishKitchenOrderEventPort;

    @Override
    public void markOrderReady(KitchenOrderId kitchenOrderId) {
        KitchenOrder kitchenOrder = loadKitchenOrderPort.getById(kitchenOrderId);
        kitchenOrder.markReady();

        saveKitchenOrderPort.save(kitchenOrder);

        publishKitchenOrderEventPort.publishKitchenOrderReady(new KitchenOrderReadyIntegrationEvent(
                kitchenOrder.getOrderId().getValue(),
                kitchenOrder.getId().getValue()));
    }
}
