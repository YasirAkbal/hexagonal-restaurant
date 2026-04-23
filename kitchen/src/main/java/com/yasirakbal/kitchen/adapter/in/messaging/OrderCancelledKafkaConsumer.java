package com.yasirakbal.kitchen.adapter.in.messaging;

import com.yasirakbal.kitchen.application.port.in.CancelKitchenOrderUseCase;
import com.yasirakbal.shared.events.OrderCancelledIntegrationEvent;
import com.yasirakbal.shared.identifier.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCancelledKafkaConsumer {

    private final CancelKitchenOrderUseCase cancelKitchenOrderUseCase;

    @KafkaListener(
            topics = "${kitchen.kafka.topics.order-cancelled}",
            groupId = "${kitchen.kafka.consumer.group-id}")
    public void onOrderCancelled(OrderCancelledIntegrationEvent event) {
        cancelKitchenOrderUseCase.cancelByOrderId(new OrderId(event.getOrderId()));
    }
}
