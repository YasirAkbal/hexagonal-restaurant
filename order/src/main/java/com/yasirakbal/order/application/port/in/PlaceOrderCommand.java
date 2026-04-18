package com.yasirakbal.order.application.port.in;

import com.yasirakbal.shared.identifier.TableId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.UUID;

//Business validations not requiring access to domain entity data should be implemented here.
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderCommand {
    @NotNull
    TableId tableId;

    @NotEmpty(message = "Order must have at least one order item.")
    List<OrderItemCommandData> orderItems;

    public static PlaceOrderCommand of(TableId tableId, List<OrderItemCommandData> orderItems) {
        return new PlaceOrderCommand(tableId, orderItems);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Value
    public static class OrderItemCommandData {

        @NotEmpty
        UUID menuId;

        @NotNull
        @Positive
        Integer quantity;

        public static OrderItemCommandData of(UUID menuId, Integer quantity) {
            return new OrderItemCommandData(menuId, quantity);
        }
    }
}
