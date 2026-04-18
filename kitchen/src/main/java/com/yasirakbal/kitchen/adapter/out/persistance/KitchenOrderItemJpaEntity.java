package com.yasirakbal.kitchen.adapter.out.persistance;

import com.yasirakbal.kitchen.application.domain.model.KitchenOrderId;
import com.yasirakbal.kitchen.application.domain.model.KitchenOrderItemId;
import com.yasirakbal.kitchen.application.domain.model.MenuItemId;
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
public class KitchenOrderItemJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID menuItemId;

    @Column(nullable = false)
    private Integer quantity;
}
