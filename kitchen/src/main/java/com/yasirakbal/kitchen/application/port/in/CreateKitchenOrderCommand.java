package com.yasirakbal.kitchen.application.port.in;

import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.OrderId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class CreateKitchenOrderCommand {

    @NotNull
    OrderId orderId;

    @NotEmpty
    List<CreateKitchenOrderItemCommandData> orderItems;

    public static CreateKitchenOrderCommand of(OrderId orderId,
                                            List<CreateKitchenOrderItemCommandData> orderItems) {

        return new CreateKitchenOrderCommand(orderId, orderItems);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Value
    public static class CreateKitchenOrderItemCommandData {

        @NotNull
        MenuItemId menuItemId;

        @NotNull
        @Positive
        Integer quantity;
    }
}
