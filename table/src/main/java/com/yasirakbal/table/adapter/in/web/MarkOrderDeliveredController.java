package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.table.application.domain.model.TableId;
import com.yasirakbal.table.application.port.in.MarkOrderDeliveredUseCase;
import com.yasirakbal.table.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class MarkOrderDeliveredController {

    private final MarkOrderDeliveredUseCase markOrderDeliveredUseCase;

    @PostMapping("api/tables/{id}/deliver")
    public ResponseEntity<Void> markOrderDelivered(@PathVariable UUID id) {
        TableId tableId = new TableId(id);
        markOrderDeliveredUseCase.markOrderDelivered(tableId);
        return ResponseEntity.noContent().build();
    }
}

