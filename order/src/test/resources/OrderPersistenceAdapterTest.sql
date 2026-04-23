-- Database state for OrderPersistenceAdapterTest.loadsOrder (domain load + JPA mapping)
INSERT INTO order_jpa_entity (id, table_id, total_amount, status, created_at)
VALUES ('11111111-1111-1111-1111-111111111111',
        '22222222-2222-2222-2222-222222222222',
        35.00,
        'PENDING',
        TIMESTAMP '2024-01-01 12:00:00');

INSERT INTO order_item_jpa_entity (id, menu_item_id, price, quantity, order_id)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
        10.00,
        2,
        '11111111-1111-1111-1111-111111111111'),
       ('cccccccc-cccc-cccc-cccc-cccccccccccc',
        'dddddddd-dddd-dddd-dddd-dddddddddddd',
        15.00,
        1,
        '11111111-1111-1111-1111-111111111111');
