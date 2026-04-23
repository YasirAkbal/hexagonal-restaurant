package com.yasirakbal.shared.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedIntegrationEvent {

    private UUID orderId;

    private UUID tableId;

    private List<OrderPlacedLineItem> items;
}
