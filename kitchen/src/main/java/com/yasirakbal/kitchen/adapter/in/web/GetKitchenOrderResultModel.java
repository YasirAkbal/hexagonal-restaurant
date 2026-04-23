package com.yasirakbal.kitchen.adapter.in.web;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class GetKitchenOrderResultModel {

    private UUID kitchenOrderId;

    private UUID orderId;

    private KitchenOrderStatus status;

    private LocalDateTime receivedAt;

    private List<ItemView> orderItems;

    @Data
    public static class ItemView {

        private UUID kitchenOrderItemId;

        private UUID menuItemId;

        private Integer quantity;
    }
}
