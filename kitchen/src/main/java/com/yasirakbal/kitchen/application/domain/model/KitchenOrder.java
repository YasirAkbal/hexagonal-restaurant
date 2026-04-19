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

    @Getter
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

    public void cancel() {
        if(status.equals(KitchenOrderStatus.READY)) {
            throw new IllegalArgumentException("Prepared orders cannot be cancelled.");
        }

        status = KitchenOrderStatus.CANCELLED;
    }

    public void startPreparing() {
        if(!status.equals(KitchenOrderStatus.RECEIVED)) {
            throw new IllegalArgumentException("Only kitchen orders with received status can be prepared.");
        }

        status = KitchenOrderStatus.PREPARING;
    }

    public void markReady() {
        if(!status.equals(KitchenOrderStatus.PREPARING)) {
            throw new IllegalArgumentException("Only kitchen orders with preparing status can be marked as ready.");
        }

        status = KitchenOrderStatus.READY;
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

    public static KitchenOrder reconstruct(KitchenOrderId id, OrderId orderId, List<KitchenOrderItemSnapshot> itemsSnapshot,
                                           KitchenOrderStatus status, LocalDateTime receivedAt) {

        List<KitchenOrderItem> items = itemsSnapshot.stream()
                .map(k -> KitchenOrderItem.of(
                        k.id(),
                        k.kitchenOrderId(),
                        k.menuItemId(),
                        k.quantity()
                ))
                .toList();

        return new KitchenOrder(
                id,
                orderId,
                items,
                status,
                receivedAt
        );
    }
}
