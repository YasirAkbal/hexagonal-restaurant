package com.yasirakbal.order.application.port.in;

import com.yasirakbal.order.application.domain.model.OrderId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class AddItemToOrderCommand {

    @NotNull
    OrderId orderId;

    @NotNull
    UUID menuId;

    @NotNull
    @Positive
    Integer quantity;

    public static AddItemToOrderCommand of(OrderId orderId, UUID menuId, Integer quantity) {
        return new AddItemToOrderCommand(orderId, menuId, quantity);
    }
}