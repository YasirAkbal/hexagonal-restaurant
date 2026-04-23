package com.yasirakbal.order.common.config;

import com.yasirakbal.order.application.domain.service.*;
import com.yasirakbal.order.application.port.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

    @Bean
    public PlaceOrderService placeOrderService(
            SaveOrderPort saveOrderPort,
            LoadTableStatusPort loadTableStatusPort,
            LoadMenuInfoPort loadMenuInfoPort,
            PublishOrderPlacedPort publishOrderPlacedPort) {

        return new PlaceOrderService(saveOrderPort, loadTableStatusPort, loadMenuInfoPort, publishOrderPlacedPort);
    }

    @Bean
    public CancelOrderService cancelOrderService(
            LoadOrderPort loadOrderPort,
            SaveOrderPort saveOrderPort,
            PublishOrderCancelledPort publishOrderCancelledPort) {

        return new CancelOrderService(loadOrderPort, saveOrderPort, publishOrderCancelledPort);
    }

    @Bean
    public AddItemToOrderService addItemToOrderService(
            LoadMenuInfoPort loadMenuInfoPort,
            SaveOrderPort saveOrderPort,
            LoadOrderPort loadOrderPort) {

        return new AddItemToOrderService(loadMenuInfoPort, saveOrderPort, loadOrderPort);
    }

    @Bean
    public UpdateOrderStatusService updateOrderStatusService(
            LoadOrderPort loadOrderPort,
            SaveOrderPort saveOrderPort) {

        return new UpdateOrderStatusService(loadOrderPort, saveOrderPort);
    }
}