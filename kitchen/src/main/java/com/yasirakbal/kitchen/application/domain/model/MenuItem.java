package com.yasirakbal.kitchen.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.yasirakbal.shared.identifier.MenuItemId;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuItem {

    private final MenuItemId id;

    private final String name;

    private final BigDecimal price;

    private final String description;

    public static MenuItem withId(MenuItemId id, String name,
                                  BigDecimal price, String description) {
        return new MenuItem(id, name, price, description);
    }

    public static MenuItem withoutId(String name,
                                  BigDecimal price, String description) {
        return new MenuItem(null, name, price, description);
    }
}
