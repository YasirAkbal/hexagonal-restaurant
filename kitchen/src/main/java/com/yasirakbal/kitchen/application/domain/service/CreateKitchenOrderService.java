package com.yasirakbal.kitchen.application.domain.service;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderItemData;
import com.yasirakbal.kitchen.application.port.in.CreateKitchenOrderCommand;
import com.yasirakbal.kitchen.application.port.in.CreateKitchenOrderUseCase;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateKitchenOrderService implements CreateKitchenOrderUseCase {

    private final SaveKitchenOrderPort saveKitchenOrderPort;

    @Override
    public void create(CreateKitchenOrderCommand command) {
        List<KitchenOrderItemData> kitchenOrderItemsData = command.getOrderItems().stream()
                .map(o -> KitchenOrderItemData.of(
                        o.getMenuItemId(),
                        o.getQuantity()
                ))
                .toList();

        KitchenOrder kitchenOrder = KitchenOrder.create(command.getOrderId(), kitchenOrderItemsData);

        saveKitchenOrderPort.save(kitchenOrder);
    }
}
