package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.service.CancelOrderService;
import com.yasirakbal.order.common.annotation.WebAdapter;
import com.yasirakbal.shared.identifier.OrderId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@WebAdapter
@RequiredArgsConstructor
@Validated
public class CancelOrderController {

    private final CancelOrderService cancelOrderService;

    @PostMapping("api/orders/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable @NotNull @Positive UUID id) {
        cancelOrderService.cancelOrder(new OrderId(id));

        return ResponseEntity.noContent().build();
    }
}
