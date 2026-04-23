package com.yasirakbal.shared.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedLineItem {

    private UUID menuItemId;

    private Integer quantity;
}
