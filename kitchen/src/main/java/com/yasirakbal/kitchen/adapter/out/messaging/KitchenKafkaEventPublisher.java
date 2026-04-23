package com.yasirakbal.kitchen.adapter.out.messaging;

import com.yasirakbal.kitchen.application.port.out.PublishKitchenOrderEventPort;
import com.yasirakbal.shared.events.KitchenOrderReadyIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KitchenKafkaEventPublisher implements PublishKitchenOrderEventPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kitchen.kafka.topics.kitchen-order-ready}")
    private String kitchenOrderReadyTopic;

    @Override
    public void publishKitchenOrderReady(KitchenOrderReadyIntegrationEvent event) {
        kafkaTemplate.send(kitchenOrderReadyTopic, event.getOrderId().toString(), event);
    }
}
