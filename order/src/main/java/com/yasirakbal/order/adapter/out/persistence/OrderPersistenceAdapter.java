package com.yasirakbal.order.adapter.out.persistence;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.GetOrderUseCase;
import com.yasirakbal.order.application.port.out.SaveOrderPort;
import com.yasirakbal.order.application.port.out.LoadOrderPort;
import com.yasirakbal.order.common.annotation.PersistenceAdapter;
import com.yasirakbal.shared.identifier.OrderId;
import lombok.AllArgsConstructor;

/*
 * Usually Persistence Adapters don't implement Use Cases, but I intentionally took a shortcut here,
 * because getting an order by id is not that complicated to have its own use case implementation for now
 */
@PersistenceAdapter
@AllArgsConstructor
public class OrderPersistenceAdapter implements SaveOrderPort, LoadOrderPort, GetOrderUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    @Override
    public void saveOrder(Order order) {
        OrderJpaEntity orderJpa = orderMapper.mapToOrderJpaEntity(order);
        orderJpaRepository.save(orderJpa);
    }

    @Override
    public Order getById(OrderId orderId) {
        OrderJpaEntity orderJpaEntity = orderJpaRepository
                .findById(orderId.getValue())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Order not found with given id = %s"
                                        .formatted(orderId.getValue())
                        )
                );

        return orderMapper.mapToOrder(orderJpaEntity);
    }

    @Override
    public Order getOrder(OrderId orderId) {
        return getById(orderId);
    }
}
