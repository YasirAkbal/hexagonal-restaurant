package com.yasirakbal.order.adapter.in.messaging;

import com.yasirakbal.order.application.domain.model.OrderStatus;
import com.yasirakbal.order.application.port.in.UpdateOrderStatusCommand;
import com.yasirakbal.order.application.port.in.UpdateOrderStatusUseCase;
import com.yasirakbal.shared.events.TableOrderDeliveredIntegrationEvent;
import com.yasirakbal.shared.identifier.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TableOrderDeliveredConsumer {

    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    @KafkaListener(
            topics = "${order.kafka.topics.table-order-delivered}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void onTableOrderDelivered(TableOrderDeliveredIntegrationEvent integrationEvent) {
        updateOrderStatusUseCase.updateStatus(UpdateOrderStatusCommand.of(
                new OrderId(integrationEvent.getOrderId()),
                OrderStatus.DELIVERED));
    }
}
