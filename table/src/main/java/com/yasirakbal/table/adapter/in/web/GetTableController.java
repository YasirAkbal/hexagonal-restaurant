package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.port.in.GetTableUseCase;
import com.yasirakbal.table.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class GetTableController {

    private final GetTableUseCase getTableUseCase;
    private final TableToGetTableResultModelMapper tableMapper;

    @GetMapping("api/tables/{id}")
    public ResponseEntity<GetTableResultModel> getTable(@PathVariable("id") UUID id) {
        TableId tableId = new TableId(id);
        Table table = getTableUseCase.getById(tableId);

        GetTableResultModel result = tableMapper.map(table);

        return ResponseEntity.ok(result);
    }
}
