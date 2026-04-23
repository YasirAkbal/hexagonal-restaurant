package com.yasirakbal.order.adapter.out.persistence;

import com.yasirakbal.order.application.domain.model.Money;
import com.yasirakbal.order.application.domain.model.Order;
import com.yasirakbal.order.application.domain.model.OrderItemData;
import com.yasirakbal.order.application.domain.model.OrderStatus;
import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.MenuItemId;
import com.yasirakbal.shared.identifier.OrderId;
import com.yasirakbal.shared.identifier.TableId;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({OrderPersistenceAdapter.class, OrderPersistenceAdapterTest.OrderMapperTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class OrderPersistenceAdapterTest {

    @TestConfiguration
    static class OrderMapperTestConfig {

        @Bean
        OrderMapper orderMapper() {
            return Mappers.getMapper(OrderMapper.class);
        }
    }

    private static final UUID LOADED_ORDER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private static final UUID LOADED_TABLE_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");

    @Autowired
    private OrderPersistenceAdapter adapterUnderTest;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Test
    @Sql("classpath:OrderPersistenceAdapterTest.sql")
    void loadsOrder() {
        Order order = adapterUnderTest.getById(new OrderId(LOADED_ORDER_ID));

        assertThat(order.getTableId().getValue()).isEqualTo(LOADED_TABLE_ID);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(order.getItemSnapshots()).hasSize(2);
        assertThat(order.calculateTotal().getAmount()).isEqualByComparingTo(new BigDecimal("35.00"));
    }

    @Test
    void savesOrder() {
        TableId tableId = new TableId(UUID.randomUUID());
        MenuItemId menuItemId = new MenuItemId(UUID.randomUUID());
        Order order = Order.placeOrder(
                tableId,
                TableStatus.AVAILABLE,
                List.of(OrderItemData.of(menuItemId, 1, Money.of(new BigDecimal("99.50"))))
        );

        adapterUnderTest.saveOrder(order);

        assertThat(orderJpaRepository.count()).isEqualTo(1);

        OrderJpaEntity saved = orderJpaRepository.findAll().getFirst();
        assertThat(saved.getTableId()).isEqualTo(tableId.getValue());
        assertThat(saved.getTotalAmount()).isEqualByComparingTo(new BigDecimal("99.50"));
        assertThat(saved.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(saved.getItems()).hasSize(1);
        assertThat(saved.getItems().getFirst().getPrice()).isEqualByComparingTo(new BigDecimal("99.50"));
        assertThat(saved.getItems().getFirst().getQuantity()).isEqualTo(1);
        assertThat(saved.getItems().getFirst().getMenuItemId()).isEqualTo(menuItemId.getValue());
    }
}
