package com.yasirakbal.table.adapter.in.web;

import com.yasirakbal.table.application.domain.model.Table;
import com.yasirakbal.table.application.domain.model.TableId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface TableToGetTableResultModelMapper {

    @Mapping(source = "id", target = "tableId")
    GetTableResultModel map(Table table);

    default UUID map(TableId tableId) {
        return tableId.getValue();
    }
}

