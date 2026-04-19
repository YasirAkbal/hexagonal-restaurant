package com.yasirakbal.configuration;

import com.yasirakbal.kitchen.adapter.out.persistance.KitchenOrderJpaRepository;
import com.yasirakbal.kitchen.adapter.out.persistance.KitchenOrderMapper;
import com.yasirakbal.kitchen.adapter.out.persistance.MenuItemJpaRepository;
import com.yasirakbal.kitchen.common.config.KitchenConfiguration;
import com.yasirakbal.order.adapter.out.persistence.OrderJpaRepository;
import com.yasirakbal.order.adapter.out.persistence.OrderMapper;
import com.yasirakbal.order.common.config.OrderConfiguration;
import com.yasirakbal.table.adapter.out.persistence.TableJpaRepository;
import com.yasirakbal.table.adapter.out.persistence.TableMapper;
import com.yasirakbal.table.common.config.TableConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
        TableJpaRepository.class,
        OrderJpaRepository.class,
        KitchenOrderJpaRepository.class,
        MenuItemJpaRepository.class
})
@EntityScan(basePackages = {
        "com.yasirakbal.table.adapter.out.persistence",
        "com.yasirakbal.order.adapter.out.persistence",
        "com.yasirakbal.kitchen.adapter.out.persistance"
})
@ComponentScan(
        basePackageClasses = {
                ConfigurationApplication.class,
                OrderMapper.class,
                TableMapper.class,
                KitchenOrderMapper.class
        },
        basePackages = {
                "com.yasirakbal.order.adapter.in.web",
                "com.yasirakbal.order.adapter.in.kafka",
                "com.yasirakbal.table.adapter.in.web"
        }
)
@Import({
        OrderConfiguration.class,
        TableConfiguration.class,
        KitchenConfiguration.class
})
public class ConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationApplication.class, args);
    }

}
