package com.yasirakbal.kitchen.application.port.integration;

import com.yasirakbal.shared.identifier.MenuItemId;

import java.math.BigDecimal;

public record MenuIntegrationResponseDTO(
        MenuItemId menuItemId,
        String name,
        BigDecimal price
) {}
