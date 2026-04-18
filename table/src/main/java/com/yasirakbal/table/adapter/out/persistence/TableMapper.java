package com.yasirakbal.table.adapter.out.persistence;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;
import com.yasirakbal.table.application.domain.model.TableStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TableMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "status", source = "status.value")
    TableJpaEntity mapToJpaEntity(Table table);

    default Table mapToDomain(TableJpaEntity jpaEntity) {
        return Table.reconstruct(
                mapToTableId(jpaEntity.getId()),
                jpaEntity.getTableNumber(),
                jpaEntity.getCapacity(),
                mapToTableStatus(jpaEntity.getStatus()),
                jpaEntity.getPendingOrderCount()
        );
    }

    default TableId mapToTableId(UUID id) {
        return new TableId(id);
    }

    default TableStatus mapToTableStatus(String status) {
        return TableStatus.valueOf(status);
    }
}
