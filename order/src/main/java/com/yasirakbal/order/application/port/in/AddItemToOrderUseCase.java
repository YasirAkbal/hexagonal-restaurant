package com.yasirakbal.order.application.port.in;

import com.yasirakbal.order.application.domain.model.Order;

public interface AddItemToOrderUseCase {
    Order addItem(AddItemToOrderCommand command);
}
