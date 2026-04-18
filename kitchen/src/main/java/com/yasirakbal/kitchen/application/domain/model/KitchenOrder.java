package com.yasirakbal.kitchen.application.domain.model;

import com.yasirakbal.shared.identifier.OrderId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KitchenOrder {

    @Getter
    private KitchenOrderId id;

    private OrderId orderId;

    private List<KitchenOrderItem> orderItems;

    private KitchenOrderStatus status;

    private LocalDateTime receivedAt;

    public static KitchenOrder create(OrderId orderId, List<KitchenOrderItemData> orderItemsData) {

        if(orderId == null || orderId.getValue() == null) {
            throw new IllegalArgumentException("Order ID must be provided");
        }

        if(orderItemsData == null || orderItemsData.isEmpty()) {
            throw new IllegalArgumentException("Kitchen Order must have at least 1 item");
        }

        KitchenOrderId kitchenOrderId = new KitchenOrderId(UUID.randomUUID());

        List<KitchenOrderItem> orderItems = orderItemsData.stream()
                .map(o -> KitchenOrderItem.of(
                        new KitchenOrderItemId(UUID.randomUUID()),
                        kitchenOrderId,
                        o.getMenuItemId(),
                        o.getQuantity()
                ))
                .toList();

        return new KitchenOrder(
                kitchenOrderId,
                orderId,
                orderItems,
                KitchenOrderStatus.RECEIVED,
                LocalDateTime.now()
        );
    }

    public List<KitchenOrderItemSnapshot> getItemSnapshots() {
        return orderItems.stream()
                .map(item -> new KitchenOrderItemSnapshot(
                        item.getId(),
                        item.getKitchenOrderId(),
                        item.getMenuItemId(),
                        item.getQuantity()))
                .toList();
    }
}
