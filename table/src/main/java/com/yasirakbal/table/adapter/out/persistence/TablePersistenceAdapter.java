package com.yasirakbal.table.adapter.out.persistence;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;
import com.yasirakbal.table.application.port.out.SaveTablePort;
import com.yasirakbal.table.application.port.out.LoadTablePort;
import com.yasirakbal.table.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TablePersistenceAdapter implements SaveTablePort, LoadTablePort {

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
}

