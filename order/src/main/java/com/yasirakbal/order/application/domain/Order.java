package com.yasirakbal.order.application.domain;

import com.sun.org.apache.xpath.internal.operations.Or;
import identifier.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private final OrderId id;

    private final TableId tableId;

    private final List<OrderItem> items;

    private final OrderStatus status;

    private final LocalDateTime createdAt;

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void placeOrder(TableId tableId) {

    }
}
