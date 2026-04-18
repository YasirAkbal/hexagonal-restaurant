package com.yasirakbal.order.application.port.in;

import com.yasirakbal.order.application.domain.model.OrderId;
import com.yasirakbal.order.application.domain.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOrderStatusCommand {

    @NotNull
    OrderId orderId;

    @NotNull
    OrderStatus orderStatus;

    public static UpdateOrderStatusCommand of(OrderId orderId, OrderStatus orderStatus) {
        return new UpdateOrderStatusCommand(orderId, orderStatus);
    }
}
