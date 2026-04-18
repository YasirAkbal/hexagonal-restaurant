package com.yasirakbal.table.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private Integer tableNumber;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer pendingOrderCount;
}

