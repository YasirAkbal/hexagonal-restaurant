package com.yasirakbal.order.application.port.in;

import identifier.TableId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.UUID;

//Business validations not requiring access to domain entity data should be implemented here.
@Value
public class PlaceOrderCommand {
    @NotNull
    TableId tableId;

    @NotEmpty(message = "Order must have at least one order item.")
    List<OrderItemCommandData> orderItems;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class OrderItemCommandData {

        private final UUID menuId;
        private final Integer quantity;

        public static OrderItemCommandData of(UUID menuId, Integer quantity) {
            return new OrderItemCommandData(menuId, quantity);
        }
    }
}
