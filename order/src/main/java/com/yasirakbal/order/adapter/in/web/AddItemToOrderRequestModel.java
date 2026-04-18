package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.OrderId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class AddItemToOrderRequestModel {

    @NotNull
    private OrderId orderId;

    @NotNull
    private UUID menuItemId;

    @NotNull
    @Positive
    private Integer quantity;
}
