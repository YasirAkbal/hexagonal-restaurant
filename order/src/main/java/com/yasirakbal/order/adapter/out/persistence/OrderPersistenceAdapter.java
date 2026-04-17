package com.yasirakbal.order.adapter.out.persistence;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.out.CreateOrderPort;
import com.yasirakbal.order.common.PersistenceAdapter;
import lombok.AllArgsConstructor;

@PersistenceAdapter
@AllArgsConstructor
public class OrderPersistenceAdapter implements CreateOrderPort {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    @Override
    public void createOrder(Order order) {
        OrderJpaEntity orderJpa = orderMapper.mapToOrderJpaEntity(order);
        orderJpaRepository.save(orderJpa);
    }
}
