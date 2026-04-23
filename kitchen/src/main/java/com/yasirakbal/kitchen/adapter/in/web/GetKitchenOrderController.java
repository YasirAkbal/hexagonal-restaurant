package com.yasirakbal.kitchen.adapter.in.web;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.GetKitchenOrderUseCase;
import com.yasirakbal.shared.identifier.OrderId;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class GetKitchenOrderController {

    private final GetKitchenOrderUseCase getKitchenOrderUseCase;
    private final KitchenOrderToGetKitchenOrderResultModelMapper kitchenOrderMapper;

    @GetMapping("api/kitchen/orders/{id}")
    public ResponseEntity<GetKitchenOrderResultModel> getByKitchenOrderId(@PathVariable("id") @NotNull UUID id) {
        KitchenOrder order = getKitchenOrderUseCase.getById(new KitchenOrderId(id));
        return ResponseEntity.ok(kitchenOrderMapper.map(order));
    }

    @GetMapping("api/kitchen/orders/by-order/{orderId}")
    public ResponseEntity<GetKitchenOrderResultModel> getByOrderId(@PathVariable("orderId") @NotNull UUID orderId) {
        return getKitchenOrderUseCase.findByOrderId(new OrderId(orderId))
                .map(kitchenOrderMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
