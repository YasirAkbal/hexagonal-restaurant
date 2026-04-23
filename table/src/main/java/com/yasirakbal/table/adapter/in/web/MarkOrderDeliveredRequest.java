package com.yasirakbal.table.adapter.in.web;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class MarkOrderDeliveredRequest {

    @NotNull
    private UUID orderId;
}
