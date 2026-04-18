package com.yasirakbal.kitchen.adapter.out.persistance;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderItemSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface KitchenOrderMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "orderId", source = "orderId.value")
    @Mapping(target = "orderItems", source = "kitchenOrder", qualifiedByName = "mapItemSnapshots")
    KitchenOrderJpaEntity mapToJpaEntity(KitchenOrder kitchenOrder);

    @Mapping(target = "id", source = "snapshot.id.value")
    @Mapping(target = "kitchenOrderId", source = "snapshot.kitchenOrderId.value")
    @Mapping(target = "menuItemId", source = "snapshot.menuItemId.value")
    @Mapping(target = "quantity", source = "snapshot.quantity")
    KitchenOrderItemJpaEntity mapToOrderItemJpaEntity(KitchenOrderItemSnapshot snapshot);

    @Named("mapItemSnapshots")
    default List<KitchenOrderItemJpaEntity> mapItemSnapshots(KitchenOrder kitchenOrder) {
        UUID kitchenOrderId = kitchenOrder.getId().getValue();
        return kitchenOrder.getItemSnapshots().stream()
                .map(this::mapToOrderItemJpaEntity)
                .toList();
    }
}
