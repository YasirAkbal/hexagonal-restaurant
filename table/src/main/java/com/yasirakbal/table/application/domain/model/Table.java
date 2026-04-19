package com.yasirakbal.table.application.domain.model;

import com.yasirakbal.shared.enums.TableStatus;
import com.yasirakbal.shared.identifier.TableId;
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
        table.status = TableStatus.AVAILABLE;

        return table;
    }

    public void markAsOccupied() {
        if (status.equals(TableStatus.OCCUPIED)) {
            throw new IllegalArgumentException("Table is already occupied with an order");
        }

        if (!status.equals(TableStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Only OPEN tables can receive orders");
        }

        status = TableStatus.OCCUPIED;
    }

    public void markAsAvailable() {
        status = TableStatus.AVAILABLE;
    }

    public static Table reconstruct(TableId id, Integer tableNumber, Integer capacity,
                                    TableStatus status) {

        return new Table(id, tableNumber, capacity, status);
    }
}

