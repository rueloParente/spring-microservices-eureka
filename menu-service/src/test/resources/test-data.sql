-- This script is used to populate the 'menu' table with sample data for testing and development.
-- The 'id' is managed by the 'menu_id_seq' sequence, so it's not specified here.
-- 'created_at', 'updated_at', and 'version' have default values or are managed by JPA/Hibernate.
truncate table menu;

-- Inserting a variety of menu items
INSERT INTO menu (code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES ('BURGER-CLASSIC', 'Classic Burger', 'A timeless beef burger with cheese, lettuce, and tomato.', 15.00, true, 100,
        NOW() - INTERVAL '2 DAY', NOW(), 1),
       ('PIZZA-MARG', 'Margherita Pizza', 'Classic pizza with tomato, mozzarella, and basil.', 12.50, true, 50, NOW() - INTERVAL '2 DAY',
        NOW(), 1),
       ('SALAD-CAESAR', 'Caesar Salad', 'Crisp romaine lettuce with Caesar dressing, croutons, and Parmesan cheese.', 10.00, true, 75,
        NOW() - INTERVAL '1 DAY', NOW(), 1),
       ('PASTA-BOLO', 'Spaghetti Bolognese', 'Spaghetti with a rich, meaty tomato sauce.', 18.25, true, 40, NOW(), NOW(), 1),
       ('STEAK-FRIES', 'Steak Frites', 'Grilled sirloin steak served with a side of golden French fries.', 25.50, true, 30, NOW(), NOW(),
        1),
       ('SANDWICH-CLUB', 'Club Sandwich', 'A triple-decker sandwich with turkey, bacon, lettuce, and tomato.', 14.00, false, 0, NOW(),
        NOW(), 1),
       ('SOUP-TOMATO', 'Tomato Soup', 'Creamy tomato soup served with a slice of toasted bread.', 8.00, true, 60, NOW(), NOW(), 1),
       ('FISH-CHIPS', 'Fish and Chips', 'Battered and fried cod served with thick-cut chips and tartar sauce.', 16.75, true, 45, NOW(),
        NOW(), 1),
       ('DESSERT-CHOC', 'Chocolate Lava Cake', 'Warm chocolate cake with a gooey molten center.', 9.50, true, 80, NOW(), NOW(), 1),
       ('DRINK-COKE', 'Coca-Cola', 'A classic can of Coca-Cola.', 2.50, true, 200, NOW(), NOW(), 1),
       ('VEG-CURRY', 'Vegetable Curry', 'A mix of seasonal vegetables in a fragrant coconut curry sauce.', 17.00, true, 55, NOW(), NOW(),
        1),
       ('WINGS-BBQ', 'BBQ Chicken Wings', 'A dozen crispy chicken wings tossed in smoky BBQ sauce.', 13.00, false, 0, NOW(), NOW(), 1);

-- Note: Adjust the sequence value if you are manually setting IDs in a test environment.
-- The following command updates the sequence to avoid conflicts after manual inserts.
-- The value should be set to the highest ID inserted + 1.
SELECT setval('menu_id_seq', (SELECT MAX(id) FROM menu) + 1, false);