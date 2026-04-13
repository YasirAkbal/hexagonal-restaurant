package com.yasirakbal.order.application.domain;

import com.sun.org.apache.xpath.internal.operations.Or;
import enums.TableStatus;
import identifier.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private final OrderId id;

    private final TableId tableId;

    private final List<OrderItem> items;

    private final OrderStatus status;

    private final LocalDateTime createdAt;

    public Order placeOrder(TableId tableId, TableStatus tableStatus) {
        if(!tableStatus.equals(TableStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Table is not available.");
        }

        return new Order(new OrderId(UUID.randomUUID()), tableId, new ArrayList<>(),
                OrderStatus.PREPARING, LocalDateTime.now());
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
