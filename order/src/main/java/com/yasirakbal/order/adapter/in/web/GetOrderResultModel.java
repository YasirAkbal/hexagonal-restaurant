package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.Money;
import com.yasirakbal.order.application.domain.model.OrderItemId;
import com.yasirakbal.order.application.domain.model.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class GetOrderResultModel {

    UUID orderId;

    UUID tableId;

    BigDecimal totalAmount;

    List<GetOrderResultView> orderItems;

    OrderStatus status;

    LocalDateTime createdAt;

    @Data
    public static class GetOrderResultView {
        OrderItemId id;

        UUID menuItemId;

        Integer quantity;

        Money price;
    }
}
