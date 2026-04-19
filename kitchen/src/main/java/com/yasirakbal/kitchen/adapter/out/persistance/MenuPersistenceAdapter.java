package com.yasirakbal.kitchen.adapter.out.persistance;

import com.yasirakbal.kitchen.application.port.integration.MenuIntegrationPort;
import com.yasirakbal.kitchen.application.port.integration.MenuIntegrationResponseDTO;
import com.yasirakbal.kitchen.common.annotation.PersistenceAdapter;
import com.yasirakbal.shared.identifier.MenuItemId;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MenuPersistenceAdapter implements MenuIntegrationPort {

    private final MenuItemJpaRepository menuItemJpaRepository;

    @Override
    public MenuIntegrationResponseDTO getMenuDetails(MenuItemId menuItemId) {
        MenuItemJpaEntity entity = menuItemJpaRepository.findById(menuItemId.getValue())
                .orElseThrow(() ->
                        new IllegalArgumentException("Menu item not found with id = %s"
                                .formatted(menuItemId.getValue()))
                );

        return new MenuIntegrationResponseDTO(
                new MenuItemId(entity.getId()),
                entity.getName(),
                entity.getPrice()
        );
    }
}
