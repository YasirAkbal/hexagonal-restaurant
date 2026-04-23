package com.yasirakbal.kitchen.adapter.in.messaging;

import com.yasirakbal.kitchen.application.port.in.CreateKitchenOrderCommand;
import com.yasirakbal.kitchen.application.port.in.CreateKitchenOrderUseCase;
import com.yasirakbal.shared.events.OrderPlacedIntegrationEvent;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderPlacedKafkaConsumer {

    private final CreateKitchenOrderUseCase createKitchenOrderUseCase;

    @KafkaListener(
            topics = "${kitchen.kafka.topics.order-placed}",
            groupId = "${kitchen.kafka.consumer.group-id}")
    public void onOrderPlaced(OrderPlacedIntegrationEvent event) {
        List<CreateKitchenOrderCommand.CreateKitchenOrderItemCommandData> items = event.getItems().stream()
                .map(i -> CreateKitchenOrderCommand.CreateKitchenOrderItemCommandData.of(
                        new MenuItemId(i.getMenuItemId()),
                        i.getQuantity()))
                .toList();

        createKitchenOrderUseCase.create(CreateKitchenOrderCommand.of(
                new OrderId(event.getOrderId()),
                items));
    }
}
