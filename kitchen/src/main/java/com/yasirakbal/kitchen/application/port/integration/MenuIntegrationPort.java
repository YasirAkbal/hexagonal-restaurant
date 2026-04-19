package com.yasirakbal.kitchen.application.port.integration;

import com.yasirakbal.shared.identifier.MenuItemId;

public interface MenuIntegrationPort {

    MenuIntegrationResponseDTO getMenuDetails(MenuItemId menuItemId);
}
