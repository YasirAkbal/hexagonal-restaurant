package com.yasirakbal.order.application.domain.model;

import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.TableId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderPlaceOrderTest {

    @Test
    void placeOrder_succeeds_whenTableIsAvailable() {
        TableId tableId = new TableId(UUID.randomUUID());
        MenuItemId menuItemId = new MenuItemId(UUID.randomUUID());
        List<OrderItemData> items = List.of(
                OrderItemData.of(menuItemId, 2, Money.of(new BigDecimal("12.50"))),
                OrderItemData.of(menuItemId, 1, Money.of(new BigDecimal("25"))),
                OrderItemData.of(menuItemId, 4, Money.of(new BigDecimal("10")))
        );

        Order order = Order.placeOrder(tableId, TableStatus.AVAILABLE, items);

        assertThat(order.getTableId()).isEqualTo(tableId);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(order.getItems()).hasSize(3);
        assertThat(order.getItems().getFirst().getMenuItemId()).isEqualTo(menuItemId);
        assertThat(order.getItems().getFirst().getQuantity()).isEqualTo(2);
        assertThat(order.calculateTotal().getAmount()).isEqualByComparingTo(new BigDecimal("90.00"));
        assertThat(order.getCreatedAt()).isNotNull();
    }

    @Test
    void placeOrder_fails_whenTableIsOccupied() {
        TableId tableId = new TableId(UUID.randomUUID());
        MenuItemId menuItemId = new MenuItemId(UUID.randomUUID());
        List<OrderItemData> items = List.of(
                OrderItemData.of(menuItemId, 1, Money.of(BigDecimal.ONE))
        );

        assertThatThrownBy(() -> Order.placeOrder(tableId, TableStatus.OCCUPIED, items))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is not available for placing an order");
    }

    @Test
    void placeOrder_fails_whenItemsEmpty() {
        TableId tableId = new TableId(UUID.randomUUID());

        assertThatThrownBy(() -> Order.placeOrder(tableId, TableStatus.AVAILABLE, List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("at least 1 item");
    }

    @Test
    void placeOrder_fails_whenItemsNull() {
        TableId tableId = new TableId(UUID.randomUUID());

        assertThatThrownBy(() -> Order.placeOrder(tableId, TableStatus.AVAILABLE, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("at least 1 item");
    }
}
