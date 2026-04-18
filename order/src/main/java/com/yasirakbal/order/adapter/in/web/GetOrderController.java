package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.GetOrderUseCase;
import com.yasirakbal.order.common.WebAdapter;
import com.yasirakbal.shared.identifier.OrderId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@WebAdapter
@RequiredArgsConstructor
@Validated
public class GetOrderController {

    private GetOrderUseCase getOrderUseCase;
    private OrderToGetOrderResultModelMapper orderToGetOrderResultModelMapper;

    @GetMapping("api/orders/{id}")
    public ResponseEntity<GetOrderResultModel> getOrder(@PathVariable @NotNull @Positive UUID orderId) {
        Order order = getOrderUseCase.getOrder(new OrderId(orderId));

        GetOrderResultModel orderDto = orderToGetOrderResultModelMapper.map(order);

        return ResponseEntity.ok(orderDto);
    }
}
