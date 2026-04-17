package com.yasirakbal.order.application.domain.model;

import com.yasirakbal.order.application.port.out.OrderItemSnapshot;
import enums.TableStatus;
import identifier.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Order {

    private OrderId id;

    private TableId tableId;

    private List<OrderItem> items;

    private OrderStatus status;

    private LocalDateTime createdAt;

    public static Order withId(OrderId id) {
        return new Order(id, null, null, null, null);
    }

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
                .map(data -> order.addOrderItem(
                        data.getMenuItemId(),
                        data.getQuantity(),
                        Money.of(data.getPrice())))
                .toList();

        return order;
    }

    private OrderItem addOrderItem(UUID menuItemId, Integer quantity, Money price) {
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
}
