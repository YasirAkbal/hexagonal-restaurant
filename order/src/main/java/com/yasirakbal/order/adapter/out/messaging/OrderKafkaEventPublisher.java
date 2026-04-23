package com.yasirakbal.order.adapter.out.messaging;

import com.yasirakbal.order.application.port.out.PublishOrderCancelledPort;
import com.yasirakbal.order.application.port.out.PublishOrderPlacedPort;
import com.yasirakbal.shared.events.OrderCancelledIntegrationEvent;
import com.yasirakbal.shared.events.OrderPlacedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderKafkaEventPublisher implements PublishOrderPlacedPort, PublishOrderCancelledPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${order.kafka.topics.order-placed}")
    private String orderPlacedTopic;

    @Value("${order.kafka.topics.order-cancelled}")
    private String orderCancelledTopic;

    @Override
    public void publishOrderPlaced(OrderPlacedIntegrationEvent event) {
        kafkaTemplate.send(orderPlacedTopic, event.getOrderId().toString(), event);
    }

    @Override
    public void publishOrderCancelled(OrderCancelledIntegrationEvent event) {
        kafkaTemplate.send(orderCancelledTopic, event.getOrderId().toString(), event);
    }
}
