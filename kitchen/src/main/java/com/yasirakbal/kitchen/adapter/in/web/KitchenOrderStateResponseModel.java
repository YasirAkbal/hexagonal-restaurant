package com.yasirakbal.kitchen.adapter.in.web;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class KitchenOrderStateResponseModel {

    UUID kitchenOrderId;
    UUID orderId;
    KitchenOrderStatus status;

    public static KitchenOrderStateResponseModel from(KitchenOrder order) {
        return new KitchenOrderStateResponseModel(
                order.getId().getValue(),
                order.getOrderId().getValue(),
                order.getStatus());
    }
}
