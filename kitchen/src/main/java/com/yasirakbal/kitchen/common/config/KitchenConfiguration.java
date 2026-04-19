package com.yasirakbal.kitchen.common.config;

import com.yasirakbal.kitchen.adapter.out.persistance.KitchenOrderJpaRepository;
import com.yasirakbal.kitchen.adapter.out.persistance.KitchenOrderMapper;
import com.yasirakbal.kitchen.adapter.out.persistance.KitchenOrderPersistenceAdapter;
import com.yasirakbal.kitchen.application.domain.service.*;
import com.yasirakbal.kitchen.application.port.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KitchenConfiguration {

    @Bean
    public KitchenOrderPersistenceAdapter kitchenOrderPersistenceAdapter(
            KitchenOrderJpaRepository kitchenOrderJpaRepository,
            KitchenOrderMapper kitchenOrderMapper) {

        return new KitchenOrderPersistenceAdapter(kitchenOrderJpaRepository, kitchenOrderMapper);
    }

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
            SaveKitchenOrderPort saveKitchenOrderPort) {

        return new MarkOrderReadyService(loadKitchenOrderPort, saveKitchenOrderPort);
    }
}