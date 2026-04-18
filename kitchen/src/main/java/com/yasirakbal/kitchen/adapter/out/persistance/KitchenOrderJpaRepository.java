package com.yasirakbal.kitchen.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface KitchenOrderJpaRepository extends JpaRepository<KitchenOrderJpaEntity, UUID> {
}
