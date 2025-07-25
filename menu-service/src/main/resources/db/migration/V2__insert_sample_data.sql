INSERT INTO menu (id, code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES (nextval('menu_id_seq'), 'APP-BRUSCH', 'Bruschetta', 'Toasted bread with fresh tomatoes, garlic, and basil.', 7.50, true, 40, NOW(),
        NOW(), 0),
       (nextval('menu_id_seq'), 'APP-WINGS', 'BBQ Chicken Wings', 'Spicy and tangy chicken wings, served with blue cheese dip.', 12.00,
        true, 30, NOW(), NOW(), 0);

INSERT INTO menu (id, code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES (nextval('menu_id_seq'), 'BURGER-CLASSIC', 'Classic Cheeseburger',
        'A juicy beef patty with cheddar cheese, lettuce, tomato, and our special sauce.', 14.50, true, 50, NOW(), NOW(), 0),
       (nextval('menu_id_seq'), 'BURGER-VEGGIE', 'Veggie Burger', 'A delicious plant-based patty with avocado and sprouts.', 13.00, true,
        25, NOW(), NOW(), 0);

INSERT INTO menu (id, code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES (nextval('menu_id_seq'), 'PIZZA-MARG', 'Margherita Pizza', 'Classic pizza with tomato, mozzarella, and fresh basil.', 15.00, true,
        50, NOW(), NOW(), 0),
       (nextval('menu_id_seq'), 'PIZZA-PEP', 'Pepperoni Pizza', 'A crowd-pleaser with spicy pepperoni and mozzarella.', 17.00, true, 45,
        NOW(), NOW(), 0);

INSERT INTO menu (id, code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES (nextval('menu_id_seq'), 'SALAD-CAESAR', 'Caesar Salad',
        'Crisp romaine lettuce with Caesar dressing, croutons, and Parmesan cheese.', 10.50, true, 100, NOW(), NOW(), 0),
       (nextval('menu_id_seq'), 'SALAD-GREEK', 'Greek Salad', 'A fresh mix of tomatoes, cucumbers, olives, and feta cheese.', 11.00, false,
        0, NOW(), NOW(), 0);

INSERT INTO menu (id, code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES (nextval('menu_id_seq'), 'PASTA-BOLO', 'Spaghetti Bolognese', 'Traditional spaghetti with a rich meat sauce.', 16.50, true, 35,
        NOW(), NOW(), 0),
       (nextval('menu_id_seq'), 'PASTA-CARB', 'Fettuccine Carbonara', 'Creamy pasta with pancetta, egg, and Parmesan cheese.', 17.50, true,
        30, NOW(), NOW(), 0);


INSERT INTO menu (id, code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES (nextval('menu_id_seq'), 'DESSERT-CHOC', 'Chocolate Lava Cake',
        'Warm chocolate cake with a gooey center, served with vanilla ice cream.', 8.00, true, 60, NOW(), NOW(), 0),
       (nextval('menu_id_seq'), 'DESSERT-TIRA', 'Tiramisu', 'Classic Italian coffee-flavored dessert.', 9.00, true, 40, NOW(), NOW(), 0);

INSERT INTO menu (id, code, name, description, price, available, current_stock, created_at, updated_at, version)
VALUES (nextval('menu_id_seq'), 'DRINK-COKE', 'Coca-Cola', 'A classic 330ml can of Coca-Cola.', 2.50, true, 200, NOW(), NOW(), 0),
       (nextval('menu_id_seq'), 'DRINK-LEMON', 'Fresh Lemonade', 'Homemade lemonade with fresh lemons and mint.', 4.00, true, 150, NOW(),
        NOW(), 0),
       (nextval('menu_id_seq'), 'DRINK-BEER', 'Craft Beer', 'A pint of our locally sourced craft IPA.', 6.50, true, 80, NOW(), NOW(), 0);
