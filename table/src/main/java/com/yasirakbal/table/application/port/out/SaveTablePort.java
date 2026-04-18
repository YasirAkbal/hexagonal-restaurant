package com.yasirakbal.table.application.port.out;

import com.yasirakbal.table.application.domain.model.Table;

public interface SaveTablePort {
    void save(Table table);
}

