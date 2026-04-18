package com.yasirakbal.order.application.port.in;

import com.yasirakbal.order.application.domain.model.OrderStatus;

public interface UpdateOrderStatusUseCase {
    void updateStatus(UpdateOrderStatusCommand command);
}
