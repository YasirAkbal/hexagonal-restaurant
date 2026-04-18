package com.yasirakbal.order.adapter.out.persistence;

import com.yasirakbal.order.application.domain.model.*;
import identifier.TableId;
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

    default Order mapToOrder(OrderJpaEntity jpaEntity) {
        List<OrderItemSnapshot> snapshots = jpaEntity.getItems().stream()
                .map(this::mapToOrderItemSnapshot)
                .toList();

        Order order = Order.reconstruct(
                new OrderId(jpaEntity.getId()),
                new TableId(jpaEntity.getTableId()),
                snapshots,
                jpaEntity.getStatus(),
                jpaEntity.getCreatedAt()
        );

        return order;
    }

    default OrderItemSnapshot mapToOrderItemSnapshot(OrderItemJpaEntity itemEntity) {
        return new OrderItemSnapshot(
                new OrderItemId(itemEntity.getId()),
                itemEntity.getMenuItemId(),
                itemEntity.getQuantity(),
                Money.of(itemEntity.getPrice())
        );
    }
}
