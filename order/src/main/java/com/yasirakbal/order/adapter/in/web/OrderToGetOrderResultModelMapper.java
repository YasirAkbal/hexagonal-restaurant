package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.domain.model.OrderId;
import com.yasirakbal.order.application.domain.model.OrderItemSnapshot;
import identifier.TableId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper
public interface OrderToGetOrderResultModelMapper {

    OrderToGetOrderResultModelMapper INSTANCE = Mappers.getMapper(OrderToGetOrderResultModelMapper.class);

    @Mapping(source = "id.value", target = "orderId")
    @Mapping(source = "tableId.value", target = "tableId")
    @Mapping(source = "order", target = "totalAmount", qualifiedByName = "calculateTotalAmount")
    @Mapping(source = "order", target = "orderItems", qualifiedByName = "mapItemSnapshots")
    GetOrderResultModel map(Order order);

    default UUID map(OrderId orderId) {
        return orderId.getValue();
    }

    default UUID map(TableId tableId) {
        return tableId.getValue();
    }

    @Named("calculateTotalAmount")
    default BigDecimal calculateTotalAmount(Order order) {
        return order.calculateTotal().getAmount();
    }

    @Named("mapItemSnapshots")
    default List<GetOrderResultModel.GetOrderResultView> mapItemSnapshots(Order order) {
        return order.getItemSnapshots().stream()
                .map(this::mapToGetOrderResultView)
                .toList();
    }

    GetOrderResultModel.GetOrderResultView mapToGetOrderResultView(OrderItemSnapshot snapshot);
}
