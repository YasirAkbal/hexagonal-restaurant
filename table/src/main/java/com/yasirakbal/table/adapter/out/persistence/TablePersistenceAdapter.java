package com.yasirakbal.table.adapter.out.persistence;

import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.port.in.GetTableUseCase;
import com.yasirakbal.table.application.port.integration.TableIntegrationPort;
import com.yasirakbal.table.application.port.integration.TableIntegrationResponseDTO;
import com.yasirakbal.table.application.port.out.SaveTablePort;
import com.yasirakbal.table.application.port.out.LoadTablePort;
import com.yasirakbal.table.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TablePersistenceAdapter implements SaveTablePort, LoadTablePort,
        GetTableUseCase, TableIntegrationPort {

    private final TableJpaRepository tableJpaRepository;
    private final TableMapper tableMapper;

    @Override
    public void save(Table table) {
        TableJpaEntity jpaEntity = tableMapper.mapToJpaEntity(table);
        tableJpaRepository.save(jpaEntity);
    }

    @Override
    public Table getById(TableId tableId) {
        TableJpaEntity jpaEntity = tableJpaRepository.findById(tableId.getValue())
                .orElseThrow(() ->
                        new IllegalArgumentException("Table not found with id = %s"
                                .formatted(tableId.getValue()))
                );

        return tableMapper.mapToDomain(jpaEntity);
    }

    @Override
    public TableIntegrationResponseDTO getTableDetails(TableId tableId) {
        TableJpaEntity jpaEntity = tableJpaRepository.findById(tableId.getValue())
                .orElseThrow(() ->
                        new IllegalArgumentException("Table not found with id = %s"
                                .formatted(tableId.getValue()))
                );

        return new TableIntegrationResponseDTO(new TableId(jpaEntity.getId()),
                TableStatus.valueOf(jpaEntity.getStatus()));
    }
}

