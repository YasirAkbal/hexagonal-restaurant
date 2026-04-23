package com.yasirakbal.kitchen.adapter.in.web;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.MarkOrderReadyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MarkOrderReadyController {

    private final MarkOrderReadyUseCase markOrderReadyUseCase;

    @PostMapping("api/kitchen/orders/{id}/ready")
    public ResponseEntity<Void> markReady(@PathVariable UUID id) {
        markOrderReadyUseCase.markOrderReady(new KitchenOrderId(id));
        return ResponseEntity.noContent().build();
    }
}
