package com.yasirakbal.order.adapter.in.messaging;

import com.yasirakbal.order.application.domain.model.OrderStatus;
import com.yasirakbal.order.application.port.in.UpdateOrderStatusCommand;
import com.yasirakbal.order.application.port.in.UpdateOrderStatusUseCase;
import com.yasirakbal.shared.events.KitchenOrderReadyIntegrationEvent;
import com.yasirakbal.shared.identifier.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KitchenOrderReadyConsumer {

    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    @KafkaListener(
            topics = "${order.kafka.topics.kitchen-order-ready}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void onKitchenOrderReady(KitchenOrderReadyIntegrationEvent integrationEvent) {
        updateOrderStatusUseCase.updateStatus(UpdateOrderStatusCommand.of(
                new OrderId(integrationEvent.getOrderId()),
                OrderStatus.READY));
    }
}
