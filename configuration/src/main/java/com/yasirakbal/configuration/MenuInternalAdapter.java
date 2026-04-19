package com.yasirakbal.configuration;

import com.yasirakbal.kitchen.application.port.integration.MenuIntegrationPort;
import com.yasirakbal.order.application.port.out.LoadMenuInfoPort;
import com.yasirakbal.order.application.port.out.MenuInfo;
import com.yasirakbal.shared.identifier.MenuItemId;

public class MenuInternalAdapter implements LoadMenuInfoPort {

    private final MenuIntegrationPort menuIntegrationPort;

    public MenuInternalAdapter(MenuIntegrationPort menuIntegrationPort) {
        this.menuIntegrationPort = menuIntegrationPort;
    }

    @Override
    public MenuInfo getMenuInfo(MenuItemId menuItemId) {
        var response = menuIntegrationPort.getMenuDetails(menuItemId);

        return new MenuInfo(
                response.menuItemId(),
                response.name(),
                response.price()
        );
    }
}
