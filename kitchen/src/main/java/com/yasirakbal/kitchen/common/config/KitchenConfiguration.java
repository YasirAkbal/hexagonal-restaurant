package com.yasirakbal.kitchen.common.config;

import com.yasirakbal.kitchen.application.domain.service.*;
import com.yasirakbal.kitchen.application.port.out.LoadKitchenOrderPort;
import com.yasirakbal.kitchen.application.port.out.PublishKitchenOrderEventPort;
import com.yasirakbal.kitchen.application.port.out.SaveKitchenOrderPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KitchenConfiguration {

    @Bean
    public CreateKitchenOrderService createKitchenOrderService(
            SaveKitchenOrderPort saveKitchenOrderPort) {

        return new CreateKitchenOrderService(saveKitchenOrderPort);
    }

    @Bean
    public CancelKitchenOrderService cancelKitchenOrderService(
            LoadKitchenOrderPort loadKitchenOrderPort,
            SaveKitchenOrderPort saveKitchenOrderPort) {

        return new CancelKitchenOrderService(loadKitchenOrderPort, saveKitchenOrderPort);
    }

    @Bean
    public StartPreparingService startPreparingService(
            LoadKitchenOrderPort loadKitchenOrderPort,
            SaveKitchenOrderPort saveKitchenOrderPort) {

        return new StartPreparingService(loadKitchenOrderPort, saveKitchenOrderPort);
    }

    @Bean
    public MarkOrderReadyService markOrderReadyService(
            LoadKitchenOrderPort loadKitchenOrderPort,
            SaveKitchenOrderPort saveKitchenOrderPort,
            PublishKitchenOrderEventPort publishKitchenOrderEventPort) {

        return new MarkOrderReadyService(loadKitchenOrderPort, saveKitchenOrderPort, publishKitchenOrderEventPort);
    }
}