package com.yasirakbal.order.application.port.in;

import com.yasirakbal.order.application.domain.model.OrderItemData;
import enums.TableStatus;
import identifier.TableId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

//Business validations not requiring access to domain entity data should be implemented here.
@Value
public class PlaceOrderCommand {
    @NotNull
    TableId tableId;

    @NotNull
    TableStatus tableStatus;

    @NotEmpty(message = "Order must have at least one order item.")
    List<OrderItemData> orderItems;
}
