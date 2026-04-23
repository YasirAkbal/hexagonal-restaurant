package com.yasirakbal.configuration.bootstrap;

import com.yasirakbal.configuration.adapter.internal.MenuInternalAdapter;
import com.yasirakbal.configuration.adapter.internal.TableInternalAdapter;
import com.yasirakbal.kitchen.application.port.integration.MenuIntegrationPort;
import com.yasirakbal.order.application.port.out.LoadMenuInfoPort;
import com.yasirakbal.order.application.port.out.LoadTableStatusPort;
import com.yasirakbal.table.application.port.integration.TableIntegrationPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public LoadTableStatusPort loadTableStatusPort(TableIntegrationPort tableIntegrationPort) {

        return new TableInternalAdapter(tableIntegrationPort);
    }

    @Bean
    public LoadMenuInfoPort loadMenuInfoPort(MenuIntegrationPort menuIntegrationPort) {

        return new MenuInternalAdapter(menuIntegrationPort);
    }

}
