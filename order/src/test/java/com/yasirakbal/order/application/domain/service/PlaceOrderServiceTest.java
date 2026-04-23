package com.yasirakbal.order.application.domain.service;

import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.port.in.PlaceOrderCommand;
import com.yasirakbal.order.application.port.out.LoadMenuInfoPort;
import com.yasirakbal.order.application.port.out.LoadTableStatusPort;
import com.yasirakbal.order.application.port.out.MenuInfo;
import com.yasirakbal.order.application.port.out.PublishOrderPlacedPort;
import com.yasirakbal.order.application.port.out.SaveOrderPort;
import com.yasirakbal.order.application.port.out.TableInfo;
import com.yasirakbal.shared.events.OrderPlacedIntegrationEvent;
import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.TableId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class PlaceOrderServiceTest {

    @Mock
    private SaveOrderPort saveOrderPort;

    @Mock
    private LoadTableStatusPort loadTableStatusPort;

    @Mock
    private LoadMenuInfoPort loadMenuInfoPort;

    @Mock
    private PublishOrderPlacedPort publishOrderPlacedPort;

    @InjectMocks
    private PlaceOrderService placeOrderService;

    private TableId tableId;
    private MenuItemId menuItemId;

    @BeforeEach
    void setUp() {
        tableId = new TableId(UUID.randomUUID());
        menuItemId = new MenuItemId(UUID.randomUUID());
    }

    @Test
    void placeOrder_loadsContextPersistsAndPublishes() {
        given(loadTableStatusPort.getTableInfo(tableId))
                .willReturn(new TableInfo(tableId, TableStatus.AVAILABLE));
        given(loadMenuInfoPort.getMenuInfo(menuItemId))
                .willReturn(new MenuInfo(menuItemId, "Burger", new BigDecimal("120.00")));

        PlaceOrderCommand command = PlaceOrderCommand.of(
                tableId,
                List.of(PlaceOrderCommand.OrderItemCommandData.of(menuItemId, 2))
        );

        placeOrderService.placeOrder(command);

        then(loadTableStatusPort).should().getTableInfo(eq(tableId));
        then(loadMenuInfoPort).should().getMenuInfo(eq(menuItemId));
        then(saveOrderPort).should().saveOrder(any(Order.class));
        then(publishOrderPlacedPort).should().publishOrderPlaced(argThat(this::eventMatchesTableAndLineItems));
    }

    private boolean eventMatchesTableAndLineItems(OrderPlacedIntegrationEvent event) {
        if (event.getOrderId() == null) {
            return false;
        }
        if (!tableId.getValue().equals(event.getTableId())) {
            return false;
        }
        if (event.getItems() == null || event.getItems().size() != 1) {
            return false;
        }
        var line = event.getItems().getFirst();
        return menuItemId.getValue().equals(line.getMenuItemId()) && Integer.valueOf(2).equals(line.getQuantity());
    }

    @Test
    void placeOrder_doesNotPublishWhenSaveFails() {
        given(loadTableStatusPort.getTableInfo(tableId))
                .willReturn(new TableInfo(tableId, TableStatus.AVAILABLE));
        given(loadMenuInfoPort.getMenuInfo(menuItemId))
                .willReturn(new MenuInfo(menuItemId, "Burger", new BigDecimal("10.00")));
        willThrow(new RuntimeException("db down")).given(saveOrderPort).saveOrder(any());

        PlaceOrderCommand command = PlaceOrderCommand.of(
                tableId,
                List.of(PlaceOrderCommand.OrderItemCommandData.of(menuItemId, 1))
        );

        assertThatThrownBy(() -> placeOrderService.placeOrder(command))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("db down");

        verifyNoInteractions(publishOrderPlacedPort);
    }
}
