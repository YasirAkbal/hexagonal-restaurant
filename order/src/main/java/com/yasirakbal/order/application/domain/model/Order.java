package com.yasirakbal.order.application.domain.model;


import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.OrderId;
import com.yasirakbal.shared.identifier.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    @Getter
    private OrderId id;

    private TableId tableId;

    private List<OrderItem> items;

    private OrderStatus status;

    private LocalDateTime createdAt;

    public static Order placeOrder(TableId tableId, TableStatus tableStatus,
                              List<OrderItemData> itemsData) {

        if (!tableStatus.equals(TableStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Table is not available.");
        }
        if (itemsData == null || itemsData.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least 1 item");
        }

        Order order = new Order();
        order.id = new OrderId(UUID.randomUUID());
        order.tableId = tableId;
        order.status = OrderStatus.PREPARING;
        order.createdAt = LocalDateTime.now();
        order.items = itemsData.stream()
                .map(data -> order.createOrderItem(
                        data.getMenuItemId(),
                        data.getQuantity(),
                        data.getPrice()))
                .toList();

        return order;
    }

    public void cancelOrder() {
        status = OrderStatus.CANCELLED;
    }

    public void updateOrderStatus(OrderStatus newOrderStatus) {
        status = newOrderStatus;
    }

    public void addItem(OrderItemData orderItemData) {
        items.add(createOrderItem(
                orderItemData.getMenuItemId(),
                orderItemData.getQuantity(),
                orderItemData.getPrice())
        );
    }

    private OrderItem createOrderItem(UUID menuItemId, Integer quantity, Money price) {
        if(isMenuItemNameDuplicated(menuItemId)) {
            throw new IllegalArgumentException("Duplicated menu item.");
        }

        OrderItemId orderItemId = new OrderItemId(UUID.randomUUID());

        return OrderItem.withId(orderItemId, menuItemId,
                quantity, price);
    }

    public Money calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(Money.ZERO, Money::add);
    }

    private boolean isMenuItemNameDuplicated(UUID menuItemId) {
        return items.stream().anyMatch(i -> i.getMenuItemId().equals(menuItemId));
    }

    public List<OrderItemSnapshot> getItemSnapshots() {
        return items.stream()
                .map(item -> new OrderItemSnapshot(
                        item.getId(),
                        item.getMenuItemId(),
                        item.getQuantity(),
                        item.getPrice()))
                .toList();
    }

    public static Order reconstruct(OrderId id, TableId tableId, List<OrderItemSnapshot> snapshots,
                                    OrderStatus status, LocalDateTime createdAt) {
        List<OrderItem> items = snapshots.stream()
                .map(s -> OrderItem.withId(
                        s.id(),
                        s.menuItemId(),
                        s.quantity(),
                        s.price()))
                .toList();

        return new Order(
                id,
                tableId,
                items,
                status,
                createdAt
        );
    }
}
