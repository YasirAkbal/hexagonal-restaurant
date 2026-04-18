package com.yasirakbal.order.application.port.out;

import java.util.UUID;

public interface LoadMenuInfoPort {
    MenuInfo getMenuInfo(UUID menuId);
}
