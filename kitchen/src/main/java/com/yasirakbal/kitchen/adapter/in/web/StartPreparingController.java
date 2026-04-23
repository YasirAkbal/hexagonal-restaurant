package com.yasirakbal.kitchen.adapter.in.web;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.port.in.StartPreparingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StartPreparingController {

    private final StartPreparingUseCase startPreparingUseCase;

    @PostMapping("api/kitchen/orders/{id}/start")
    public ResponseEntity<Void> startPreparing(@PathVariable("id") UUID id) {
        startPreparingUseCase.startPreparing(new KitchenOrderId(id));

        return ResponseEntity.noContent().build();
    }
}
