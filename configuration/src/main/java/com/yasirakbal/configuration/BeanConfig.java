package com.yasirakbal.configuration;

import com.yasirakbal.order.application.port.out.LoadMenuInfoPort;
import com.yasirakbal.order.application.port.out.LoadTableStatusPort;
import com.yasirakbal.order.application.port.out.MenuInfo;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.table.application.port.integration.TableInternalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class BeanConfig {

    @Bean
    public LoadTableStatusPort loadTableStatusPort(TableInternalService tableInternalService) {

        return new TableInternalAdapter(tableInternalService);
    }

    @Bean
    public LoadMenuInfoPort loadMenuInfoPort() {

        return menuId -> new MenuInfo(new MenuItemId(menuId), "menu", BigDecimal.ZERO);
    }

}
