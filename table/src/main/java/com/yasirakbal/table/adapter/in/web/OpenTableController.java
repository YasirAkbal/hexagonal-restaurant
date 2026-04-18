package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;
import com.yasirakbal.table.application.port.in.OpenTableUseCase;
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
public class OpenTableController {

    private final OpenTableUseCase openTableUseCase;

    @PostMapping("api/tables/{id}/open")
    public ResponseEntity<Void> openTable(@PathVariable UUID id) {
        TableId tableId = new TableId(id);
        openTableUseCase.openTable(tableId);
        return ResponseEntity.noContent().build();
    }
}

