package com.yasirakbal.table.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Table {

    private TableId id;

    private Integer tableNumber;

    private Integer capacity;

    private TableStatus status;

    private Integer pendingOrderCount;

    public static Table create(Integer tableNumber, Integer capacity) {
        if (tableNumber == null || tableNumber <= 0) {
            throw new IllegalArgumentException("Table number must be greater than 0");
        }
        if (capacity == null || capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        Table table = new Table();
        table.id = new TableId(UUID.randomUUID());
        table.tableNumber = tableNumber;
        table.capacity = capacity;
        table.status = TableStatus.OPEN;
        table.pendingOrderCount = 0;

        return table;
    }

    public void markAsOccupied() {
        if (!this.status.equals(TableStatus.OPEN)) {
            throw new IllegalArgumentException("Only OPEN tables can be marked as OCCUPIED");
        }

        this.status = TableStatus.OCCUPIED;
        this.pendingOrderCount = 1;
    }

    public void orderStarted() {
        if (this.status.equals(TableStatus.OCCUPIED)) {
            throw new IllegalArgumentException("Table is already occupied with an order");
        }

        if (!this.status.equals(TableStatus.OPEN)) {
            throw new IllegalArgumentException("Only OPEN tables can receive orders");
        }

        this.status = TableStatus.OCCUPIED;
        this.pendingOrderCount = 1;
    }

    public void close() {
        if (this.pendingOrderCount > 0) {
            throw new IllegalArgumentException("Cannot close table with pending orders");
        }

        this.status = TableStatus.CLOSED;
    }

    public void open() {
        this.status = TableStatus.OPEN;
        this.pendingOrderCount = 0;
    }

    public void markOrderAsDelivered() {
        if (this.pendingOrderCount > 0) {
            this.pendingOrderCount--;
        }
    }

    public static Table reconstruct(TableId id, Integer tableNumber, Integer capacity,
                                    TableStatus status, Integer pendingOrderCount) {

        return new Table(id, tableNumber, capacity, status, pendingOrderCount);
    }
}

