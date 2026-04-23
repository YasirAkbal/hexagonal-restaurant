package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.PlaceOrderCommand;
import com.yasirakbal.order.application.port.in.PlaceOrderUseCase;
import com.yasirakbal.order.common.annotation.WebAdapter;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.TableId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@WebAdapter
@RequiredArgsConstructor
class PlaceOrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final OrderToGetOrderResultModelMapper orderToGetOrderResultModelMapper;

    @PostMapping("api/orders")
    public ResponseEntity<GetOrderResultModel> placeOrder(@RequestBody @Valid PlaceOrderRequestModel request) {
        TableId tableId = new TableId(request.getTableId());
        List<PlaceOrderCommand.OrderItemCommandData> orderItemDataList = request.getOrderItems().stream()
                .map(o -> PlaceOrderCommand.OrderItemCommandData.of(
                            new MenuItemId(o.getMenuItemId()),
                            o.getQuantity()
                        )
                )
                .toList();


        PlaceOrderCommand placeOrderCommand = PlaceOrderCommand.of(tableId, orderItemDataList);
        Order order = placeOrderUseCase.placeOrder(placeOrderCommand);

        GetOrderResultModel body = orderToGetOrderResultModelMapper.map(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
