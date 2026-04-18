package com.yasirakbal.order.adapter.in.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
class PlaceOrderRequestModel {

    @NotNull
    private UUID tableId;

    @NotEmpty
    private List<PlaceOrderOrderItemView> orderItems;
}
