package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.port.in.UnOccupyTableUseCase;
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

    private final UnOccupyTableUseCase unOccupyTableUseCase;

    @PostMapping("api/tables/{id}/close")
    public ResponseEntity<Void> closeTable(@PathVariable UUID id) {
        TableId tableId = new TableId(id);
        unOccupyTableUseCase.unOccupyTable(tableId);
        return ResponseEntity.noContent().build();
    }
}

