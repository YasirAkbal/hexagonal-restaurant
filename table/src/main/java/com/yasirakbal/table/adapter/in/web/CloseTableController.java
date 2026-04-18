package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.table.application.domain.model.TableId;
import com.yasirakbal.table.application.port.in.CloseTableUseCase;
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
public class CloseTableController {

    private final CloseTableUseCase closeTableUseCase;

    @PostMapping("api/tables/{id}/close")
    public ResponseEntity<Void> closeTable(@PathVariable UUID id) {
        TableId tableId = new TableId(id);
        closeTableUseCase.closeTable(tableId);
        return ResponseEntity.noContent().build();
    }
}

