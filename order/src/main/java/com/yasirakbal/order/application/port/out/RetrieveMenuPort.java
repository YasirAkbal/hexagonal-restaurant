package com.yasirakbal.order.application.port.out;

import java.util.UUID;

public interface RetrieveMenuPort {
    MenuInfo getMenuInfo(UUID menuId);
}
