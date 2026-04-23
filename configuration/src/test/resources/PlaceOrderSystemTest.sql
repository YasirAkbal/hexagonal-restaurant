-- System test precondition: empty order tables so this scenario only sees its own writes.
-- Reference data (tables, menu) comes from DataSeeder at startup; this script mirrors the book's
-- explicit SQL fixture for the persistence touched by the use case under test.
DELETE FROM kitchen_order_item_jpa_entity;
DELETE FROM kitchen_order_jpa_entity;
DELETE FROM order_item_jpa_entity;
DELETE FROM order_jpa_entity;
