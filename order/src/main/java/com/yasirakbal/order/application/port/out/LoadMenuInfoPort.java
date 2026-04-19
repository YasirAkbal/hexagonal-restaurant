package com.yasirakbal.order.application.port.out;

import com.yasirakbal.shared.identifier.MenuItemId;

public interface LoadMenuInfoPort {
    MenuInfo getMenuInfo(MenuItemId menuItemId);
}
