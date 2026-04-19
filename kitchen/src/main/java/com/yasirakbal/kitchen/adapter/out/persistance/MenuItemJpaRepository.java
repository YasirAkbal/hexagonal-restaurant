package com.yasirakbal.kitchen.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuItemJpaRepository extends JpaRepository<MenuItemJpaEntity, UUID> {}
