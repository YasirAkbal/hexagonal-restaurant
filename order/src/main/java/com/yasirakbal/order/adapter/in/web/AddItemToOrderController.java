package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.AddItemToOrderCommand;
import com.yasirakbal.order.application.port.in.AddItemToOrderUseCase;
import com.yasirakbal.order.common.annotation.WebAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class AddItemToOrderController {

    private final AddItemToOrderUseCase addItemToOrderUseCase;
    private final OrderToGetOrderResultModelMapper orderToGetOrderResultModelMapper;

    @PostMapping("api/orders/orderItems")
    public ResponseEntity<GetOrderResultModel> addOrderItem(@RequestBody @Valid AddItemToOrderRequestModel request) {
        AddItemToOrderCommand command = AddItemToOrderCommand.of(
                request.getOrderId(),
                request.getMenuItemId(),
                request.getQuantity()
        );

        Order order = addItemToOrderUseCase.addItem(command);
        return ResponseEntity.ok(orderToGetOrderResultModelMapper.map(order));
    }
}
