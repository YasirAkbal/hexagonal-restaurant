package com.yasirakbal.table.common.config;

import com.yasirakbal.table.application.domain.service.OccupyTableService;
import com.yasirakbal.table.application.domain.service.UnOccupyTableService;
import com.yasirakbal.table.application.port.in.OccupyTableUseCase;
import com.yasirakbal.table.application.port.in.UnOccupyTableUseCase;
import com.yasirakbal.table.application.port.out.LoadTablePort;
import com.yasirakbal.table.application.port.out.SaveTablePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableConfiguration {

    @Bean
    public OccupyTableUseCase occupyTableUseCase(
            LoadTablePort loadTablePort,
            SaveTablePort saveTablePort) {

        return new OccupyTableService(loadTablePort, saveTablePort);
    }

    @Bean
    public UnOccupyTableUseCase unOccupyTableUseCase(
            LoadTablePort loadTablePort,
            SaveTablePort saveTablePort) {

        return new UnOccupyTableService(loadTablePort, saveTablePort);
    }
}