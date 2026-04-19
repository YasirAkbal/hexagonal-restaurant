package com.yasirakbal.order.adapter.in.kafka;

import com.yasirakbal.shared.events.TableOrderDeliveredIntegrationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TableOrderDeliveredConsumer {

    @KafkaListener(
            topics = "${order.kafka.topics.table-order-delivered}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void onTableOrderDelivered(TableOrderDeliveredIntegrationEvent integrationEvent) {

    }
}
