package com.yasirakbal.kitchen.application.domain.model;

import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Value
public class KitchenOrderId {
    UUID value;
}
