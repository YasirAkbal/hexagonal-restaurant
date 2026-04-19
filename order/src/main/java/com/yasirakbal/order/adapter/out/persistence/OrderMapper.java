package com.yasirakbal.order.adapter.out.persistence;

import com.yasirakbal.order.application.domain.model.*;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.OrderId;
import com.yasirakbal.shared.identifier.TableId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "tableId", source = "tableId.value")
    @Mapping(target = "totalAmount", source = "order", qualifiedByName = "calculateTotalAmount")
    @Mapping(target = "items", source = "order", qualifiedByName = "mapItemSnapshots")
    OrderJpaEntity mapToOrderJpaEntity(Order order);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "menuItemId", source = "menuItemId.value")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price.amount")
    OrderItemJpaEntity mapToOrderItemJpaEntity(OrderItemSnapshot snapshot);

    @Named("calculateTotalAmount")
    default BigDecimal calculateTotalAmount(Order order) {
        return order.calculateTotal().getAmount();
    }

    @Named("mapItemSnapshots")
    default List<OrderItemJpaEntity> mapItemSnapshots(Order order) {
        UUID orderId = order.getId().getValue();
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
                new MenuItemId(itemEntity.getMenuItemId()),
                itemEntity.getQuantity(),
                Money.of(itemEntity.getPrice())
        );
    }
}
