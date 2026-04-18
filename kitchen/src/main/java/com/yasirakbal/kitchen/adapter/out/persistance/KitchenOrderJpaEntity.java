package com.yasirakbal.kitchen.adapter.out.persistance;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KitchenOrderJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kitchen_order_id")
    private List<KitchenOrderItemJpaEntity> orderItems;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private KitchenOrderStatus status;

    @Column(nullable = false)
    private LocalDateTime receivedAt;
}
