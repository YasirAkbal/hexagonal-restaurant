package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.shared.identifier.OrderId;
import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.port.in.MarkOrderDeliveredUseCase;
import com.yasirakbal.table.common.WebAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class MarkOrderDeliveredController {

    private final MarkOrderDeliveredUseCase markOrderDeliveredUseCase;

    @PostMapping("api/tables/{id}/deliver")
    public ResponseEntity<Void> markDelivered(
            @PathVariable UUID id,
            @RequestBody @Valid MarkOrderDeliveredRequest request) {

        markOrderDeliveredUseCase.markOrderDelivered(
                new TableId(id),
                new OrderId(request.getOrderId()));

        return ResponseEntity.noContent().build();
    }
}
