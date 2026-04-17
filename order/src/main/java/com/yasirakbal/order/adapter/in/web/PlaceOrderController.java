package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.OrderItemData;
import com.yasirakbal.order.application.port.in.PlaceOrderCommand;
import com.yasirakbal.order.application.port.in.PlaceOrderUseCase;
import com.yasirakbal.order.common.WebAdapter;
import identifier.TableId;
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

    @PostMapping("api/orders/place")
    public ResponseEntity<Void> placeOrder(@RequestBody @Valid PlaceOrderRequestModel request) {
        TableId tableId = new TableId(request.getTableId());
        List<PlaceOrderCommand.OrderItemCommandData> orderItemDataList = request.getOrderItems().stream()
                .map(o ->
                        PlaceOrderCommand.OrderItemCommandData.of(
                                o.getMenuItemId(),
                                o.getQuantity()
                        )
                )
                .toList();


        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(tableId, orderItemDataList);
        placeOrderUseCase.placeOrder(placeOrderCommand);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
