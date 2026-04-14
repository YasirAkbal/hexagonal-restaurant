package com.yasirakbal.order.application.domain.model;

import enums.TableStatus;
import identifier.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private final OrderId id;

    private final TableId tableId;

    private final List<OrderItem> items;

    private final OrderStatus status;

    private final LocalDateTime createdAt;

    public Order placeOrder(TableId tableId, TableStatus tableStatus,
                            List<OrderItemData> itemsData) {

        if(!tableStatus.equals(TableStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Table is not available.");
        }

        if (itemsData == null || itemsData.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least 1 item");
        }

        OrderId orderId = new OrderId(UUID.randomUUID());

        List<OrderItem> items = itemsData.stream()
                .map(data -> addOrderItem(data.getMenuItemName(),
                        data.getQuantity(),
                        data.getPrice()))
                .toList();

        return new Order(orderId, tableId, items,
                OrderStatus.PREPARING, LocalDateTime.now());
    }

    private OrderItem addOrderItem(String menuItemName, Integer quantity, Money price) {
        if(isMenuItemNameDuplicated(menuItemName)) {
            throw new IllegalArgumentException("Duplicated menu item.");
        }

        OrderItemId orderItemId = new OrderItemId(UUID.randomUUID());

        return OrderItem.withId(orderItemId, menuItemName,
                quantity, price);
    }

    public Money calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(Money.ZERO, Money::add);
    }

    private boolean isMenuItemNameDuplicated(String menuItemName) {
        return items.stream().anyMatch(i -> i.getMenuItemName().equals(menuItemName));
    }
}
