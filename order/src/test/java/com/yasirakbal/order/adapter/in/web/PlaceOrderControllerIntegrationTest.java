package com.yasirakbal.order.adapter.in.web;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.PlaceOrderCommand;
import com.yasirakbal.order.application.port.in.PlaceOrderUseCase;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.TableId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlaceOrderController.class)
class PlaceOrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceOrderUseCase placeOrderUseCase;

    @MockBean
    private OrderToGetOrderResultModelMapper orderToGetOrderResultModelMapper;

    @Test
    void placeOrder_mapsJsonToCommandCallsUseCaseAndReturnsCreated() throws Exception {
        UUID tableUuid = UUID.fromString("aaaaaaaa-0000-0000-0000-000000000001");
        UUID menuItemUuid = UUID.fromString("bbbbbbbb-0000-0000-0000-000000000001");

        TableId tableId = new TableId(tableUuid);
        MenuItemId menuItemId = new MenuItemId(menuItemUuid);
        PlaceOrderCommand expectedCommand = PlaceOrderCommand.of(
                tableId,
                List.of(PlaceOrderCommand.OrderItemCommandData.of(menuItemId, 2))
        );

        Order orderFromUseCase = mock(Order.class);
        given(placeOrderUseCase.placeOrder(eq(expectedCommand))).willReturn(orderFromUseCase);
        given(orderToGetOrderResultModelMapper.map(orderFromUseCase)).willReturn(new GetOrderResultModel());

        String requestBody = """
                {
                  "tableId": "aaaaaaaa-0000-0000-0000-000000000001",
                  "orderItems": [
                    { "menuItemId": "bbbbbbbb-0000-0000-0000-000000000001", "quantity": 2 }
                  ]
                }
                """;

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        then(placeOrderUseCase).should().placeOrder(eq(expectedCommand));
    }
}
