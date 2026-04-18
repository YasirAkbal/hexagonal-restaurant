package com.yasirakbal.table.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableJpaRepository extends JpaRepository<TableJpaEntity, UUID> {
}

