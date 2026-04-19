package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.shared.identifier.TableId;
import com.yasirakbal.table.application.domain.model.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TableToGetTableResultModelMapper {

    @Mapping(source = "id", target = "tableId")
    GetTableResultModel map(Table table);

    default UUID map(TableId tableId) {
        return tableId.getValue();
    }
}

