package com.yasirakbal.order.adapter.out.persistence;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.out.OrderItemSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "tableId", source = "tableId.value")
    @Mapping(target = "totalAmount", source = "order", qualifiedByName = "calculateTotalAmount")
    @Mapping(target = "items", source = "order", qualifiedByName = "mapItemSnapshots")
    OrderJpaEntity mapToOrderJpaEntity(Order order);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "price", source = "price.amount")
    OrderItemJpaEntity mapToOrderItemJpaEntity(OrderItemSnapshot snapshot);

    @Named("calculateTotalAmount")
    default BigDecimal calculateTotalAmount(Order order) {
        return order.calculateTotal().getAmount();
    }

    @Named("mapItemSnapshots")
    default List<OrderItemJpaEntity> mapItemSnapshots(Order order) {
        return order.getItemSnapshots().stream()
                .map(this::mapToOrderItemJpaEntity)
                .toList();
    }
}

