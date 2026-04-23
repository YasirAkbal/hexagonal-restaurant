package com.yasirakbal.kitchen.application.port.in;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.shared.identifier.OrderId;

import java.util.Optional;

public interface GetKitchenOrderUseCase {
    KitchenOrder getById(KitchenOrderId kitchenOrderId);

    Optional<KitchenOrder> findByOrderId(OrderId orderId);
}
