package com.yasirakbal.order.adapter.in.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.UUID;

@Data
class PlaceOrderOrderItemView {

    @NotNull
    private UUID menuItemId;

    @NotNull
    @Positive
    private Integer quantity;
}
