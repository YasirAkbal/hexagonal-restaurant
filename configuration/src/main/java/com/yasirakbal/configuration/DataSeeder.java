package com.yasirakbal.configuration;

import com.yasirakbal.kitchen.adapter.out.persistance.MenuItemJpaEntity;
import com.yasirakbal.kitchen.adapter.out.persistance.MenuItemJpaRepository;
import com.yasirakbal.table.adapter.out.persistence.TableJpaEntity;
import com.yasirakbal.table.adapter.out.persistence.TableJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements ApplicationRunner {

    private final TableJpaRepository tableJpaRepository;
    private final MenuItemJpaRepository menuItemJpaRepository;

    public static final UUID TABLE_1_ID = UUID.fromString("aaaaaaaa-0000-0000-0000-000000000001");
    public static final UUID TABLE_2_ID = UUID.fromString("aaaaaaaa-0000-0000-0000-000000000002");
    public static final UUID TABLE_3_ID = UUID.fromString("aaaaaaaa-0000-0000-0000-000000000003");

    public static final UUID MENU_ITEM_BURGER_ID   = UUID.fromString("bbbbbbbb-0000-0000-0000-000000000001");
    public static final UUID MENU_ITEM_PIZZA_ID    = UUID.fromString("bbbbbbbb-0000-0000-0000-000000000002");
    public static final UUID MENU_ITEM_SALAD_ID    = UUID.fromString("bbbbbbbb-0000-0000-0000-000000000003");
    public static final UUID MENU_ITEM_COLA_ID     = UUID.fromString("bbbbbbbb-0000-0000-0000-000000000004");

    @Override
    public void run(ApplicationArguments args) {
        seedTables();
        seedMenuItems();
        log.info("=== Seed data loaded ===");
        log.info("Tables: {}, {}, {}", TABLE_1_ID, TABLE_2_ID, TABLE_3_ID);
        log.info("Burger: {}", MENU_ITEM_BURGER_ID);
        log.info("Pizza:  {}", MENU_ITEM_PIZZA_ID);
        log.info("Salad:  {}", MENU_ITEM_SALAD_ID);
        log.info("Cola:   {}", MENU_ITEM_COLA_ID);
    }

    private void seedTables() {
        List<TableJpaEntity> tables = List.of(
                new TableJpaEntity(TABLE_1_ID, 1, 4, "AVAILABLE"),
                new TableJpaEntity(TABLE_2_ID, 2, 2, "AVAILABLE"),
                new TableJpaEntity(TABLE_3_ID, 3, 6, "AVAILABLE")
        );
        tableJpaRepository.saveAll(tables);
    }

    private void seedMenuItems() {
        List<MenuItemJpaEntity> items = List.of(
                new MenuItemJpaEntity(MENU_ITEM_BURGER_ID, "Burger",     new BigDecimal("120.00"), "Classic beef burger"),
                new MenuItemJpaEntity(MENU_ITEM_PIZZA_ID,  "Pizza",      new BigDecimal("180.00"), "Margherita pizza"),
                new MenuItemJpaEntity(MENU_ITEM_SALAD_ID,  "Salad",      new BigDecimal("80.00"),  "Caesar salad"),
                new MenuItemJpaEntity(MENU_ITEM_COLA_ID,   "Cola",       new BigDecimal("30.00"),  "330ml can")
        );
        menuItemJpaRepository.saveAll(items);
    }
}