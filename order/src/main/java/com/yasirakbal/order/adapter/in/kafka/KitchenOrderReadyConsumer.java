package com.yasirakbal.order.adapter.in.kafka;

import com.yasirakbal.shared.events.KitchenOrderReadyIntegrationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KitchenOrderReadyConsumer {

    @KafkaListener(
            topics = "${order.kafka.topics.kitchen-order-ready}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void onKitchenOrderReady(KitchenOrderReadyIntegrationEvent integrationEvent) {

    }
}
