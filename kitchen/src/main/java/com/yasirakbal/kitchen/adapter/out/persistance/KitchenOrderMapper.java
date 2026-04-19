package com.yasirakbal.kitchen.adapter.out.persistance;

import com.yasirakbal.kitchen.application.domain.model.*;
import com.yasirakbal.shared.identifier.OrderId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;
import com.yasirakbal.shared.identifier.MenuItemId;

@Mapper(componentModel = "spring")
public interface KitchenOrderMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "orderId", source = "orderId.value")
    @Mapping(target = "orderItems", source = "kitchenOrder", qualifiedByName = "mapItemSnapshots")
    KitchenOrderJpaEntity mapToJpaEntity(KitchenOrder kitchenOrder);

    @Mapping(target = "id", source = "snapshot.id.value")
    @Mapping(target = "menuItemId", source = "snapshot.menuItemId.value")
    @Mapping(target = "quantity", source = "snapshot.quantity")
    KitchenOrderItemJpaEntity mapToOrderItemJpaEntity(KitchenOrderItemSnapshot snapshot);

    @Named("mapItemSnapshots")
    default List<KitchenOrderItemJpaEntity> mapItemSnapshots(KitchenOrder kitchenOrder) {
        return kitchenOrder.getItemSnapshots().stream()
                .map(this::mapToOrderItemJpaEntity)
                .toList();
    }

    default KitchenOrder mapToKitchenOrder(KitchenOrderJpaEntity jpaEntity) {
        KitchenOrderId kitchenOrderId = new KitchenOrderId(jpaEntity.getId());

        List<KitchenOrderItemSnapshot> itemSnapshots = jpaEntity.getOrderItems().stream()
                .map(o -> new KitchenOrderItemSnapshot(
                        new KitchenOrderItemId(o.getId()),
                        kitchenOrderId,
                        new MenuItemId(o.getMenuItemId()),
                        o.getQuantity()
                ))
                .toList();

        return KitchenOrder.reconstruct(
                kitchenOrderId,
                new OrderId(jpaEntity.getOrderId()),
                itemSnapshots,
                jpaEntity.getStatus(),
                jpaEntity.getReceivedAt()
        );
    }
}
