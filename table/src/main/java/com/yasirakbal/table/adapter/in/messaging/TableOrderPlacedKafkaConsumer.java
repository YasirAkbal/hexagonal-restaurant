package com.yasirakbal.table.adapter.in.messaging;

import com.yasirakbal.shared.events.OrderPlacedIntegrationEvent;
import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.port.in.OccupyTableUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TableOrderPlacedKafkaConsumer {

    private final OccupyTableUseCase occupyTableUseCase;

    @KafkaListener(
            topics = "${table.kafka.topics.order-placed}",
            groupId = "${table.kafka.consumer.group-id}")
    public void onOrderPlaced(OrderPlacedIntegrationEvent event) {
        occupyTableUseCase.occupyTable(new TableId(event.getTableId()));
    }
}
