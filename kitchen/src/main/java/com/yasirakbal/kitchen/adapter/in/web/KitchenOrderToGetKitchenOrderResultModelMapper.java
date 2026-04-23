package com.yasirakbal.kitchen.adapter.in.web;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrder;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderItemSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KitchenOrderToGetKitchenOrderResultModelMapper {

    @Mapping(source = "id.value", target = "kitchenOrderId")
    @Mapping(source = "orderId.value", target = "orderId")
    @Mapping(source = "order", target = "orderItems", qualifiedByName = "mapOrderItems")
    GetKitchenOrderResultModel map(KitchenOrder order);

    @Named("mapOrderItems")
    default List<GetKitchenOrderResultModel.ItemView> mapOrderItems(KitchenOrder order) {
        return order.getItemSnapshots().stream()
                .map(this::mapItem)
                .toList();
    }

    default GetKitchenOrderResultModel.ItemView mapItem(KitchenOrderItemSnapshot snapshot) {
        GetKitchenOrderResultModel.ItemView view = new GetKitchenOrderResultModel.ItemView();
        view.setKitchenOrderItemId(snapshot.id().getValue());
        view.setMenuItemId(snapshot.menuItemId().getValue());
        view.setQuantity(snapshot.quantity());
        return view;
    }
}
