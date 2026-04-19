package com.yasirakbal.order.application.domain.model;


import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.OrderId;
import com.yasirakbal.shared.identifier.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.yasirakbal.shared.identifier.MenuItemId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    @Getter
    private OrderId id;

    @Getter
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
        if(!status.equals(OrderStatus.PENDING)) {
            throw new IllegalArgumentException("Only PENDING orders can be cancelled");
        }

        status = OrderStatus.CANCELLED;
    }

    public void updateOrderStatus(OrderStatus newOrderStatus) {
        status = newOrderStatus;
    }

    public void addItem(OrderItemData orderItemData) {
        if(status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            throw new IllegalArgumentException("New order items cannot be added into delivered or cancelled orders.");
        }

        Optional<OrderItem> currOrderItem = getOrderItemFromCurrentList(orderItemData.getMenuItemId());

        if(currOrderItem.isPresent())  {
            OrderItem currOrderItemUnwrapped = currOrderItem.get();
            currOrderItemUnwrapped.setQuantity(currOrderItemUnwrapped.getQuantity() + orderItemData.getQuantity());
        } else {
            items.add(createOrderItem(
                    orderItemData.getMenuItemId(),
                    orderItemData.getQuantity(),
                    orderItemData.getPrice())
            );
        }
    }

    private OrderItem createOrderItem(MenuItemId menuItemId, Integer quantity, Money price) {
        OrderItemId orderItemId = new OrderItemId(UUID.randomUUID());

        return OrderItem.withId(orderItemId, menuItemId,
                quantity, price);
    }

    public Money calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(Money.ZERO, Money::add);
    }

    private Optional<OrderItem> getOrderItemFromCurrentList(MenuItemId menuItemId) {
        return items.stream().filter(i -> i.getMenuItemId().equals(menuItemId)).findFirst();
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
