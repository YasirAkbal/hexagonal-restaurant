package com.yasirakbal.table.adapter.out.messaging;

import com.yasirakbal.shared.events.TableOrderDeliveredIntegrationEvent;
import com.yasirakbal.table.application.port.out.PublishTableOrderDeliveredPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TableKafkaEventPublisher implements PublishTableOrderDeliveredPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${table.kafka.topics.table-order-delivered}")
    private String tableOrderDeliveredTopic;

    @Override
    public void publishOrderDelivered(TableOrderDeliveredIntegrationEvent event) {
        kafkaTemplate.send(tableOrderDeliveredTopic, event.getOrderId().toString(), event);
    }
}
