INSERT INTO regions (id, name) VALUES (2, 'Norteamérica');
INSERT INTO regions (id, name) VALUES (3, 'Europa');
INSERT INTO regions (id, name) VALUES (4, 'Asia');
INSERT INTO regions (id, name) VALUES (5, 'África');
INSERT INTO regions (id, name) VALUES (6, 'Oceanía');
INSERT INTO regions (id, name) VALUES (7, 'Antártida');
INSERT INTO regions (id, name) VALUES (8, 'América Central');
INSERT INTO regions (id, name) VALUES (9, 'Caribe');
INSERT INTO regions (id, name) VALUES (10, 'Medio Oriente');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (3, 'Sofía', 'López', '2023-08-29', 'sofia@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (7, 'Juan', 'García', '2023-08-29', 'juan@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (2, 'María', 'Martínez', '2023-08-29', 'maria@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (10, 'Pedro', 'Rodríguez', '2023-08-29', 'pedro@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (5, 'Ana', 'Pérez', '2023-08-29', 'ana@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (8, 'Carlos', 'Gómez', '2023-08-29', 'carlos@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (4, 'Laura', 'Hernández', '2023-08-29', 'laura@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (6, 'José', 'Díaz', '2023-08-29', 'jose@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (9, 'Isabel', 'López', '2023-08-29', 'isabel@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (1, 'Luis', 'Sánchez', '2023-08-29', 'luis@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (10, 'Marta', 'Ramírez', '2023-08-29', 'marta@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (3, 'Alejandro', 'Torres', '2023-08-29', 'alejandro@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (7, 'Eva', 'Flores', '2023-08-29', 'eva@email.com');

INSERT INTO clients (region_id, name, last_name, create_at, email)
VALUES (2, 'Javier', 'Ortega', '2023-08-29', 'javier@email.com');

INSERT INTO products (name, price, create_at) VALUES('Panasonic Pantalla LCD', 300, NOW());

INSERT INTO products (name, price, create_at) VALUES('Samsung Galaxy S20', 799, NOW());

INSERT INTO products (name, price, create_at) VALUES('Sony Bravia TV LED', 549, NOW());

INSERT INTO products (name, price, create_at) VALUES('Apple MacBook Pro', 1499, NOW());

INSERT INTO products (name, price, create_at) VALUES('Canon EOS 5D Mark IV', 2499, NOW());

INSERT INTO products (name, price, create_at) VALUES('Bose QuietComfort 35 II', 349, NOW());

INSERT INTO products (name, price, create_at) VALUES('LG 55" OLED TV', 999, NOW());

INSERT INTO products (name, price, create_at) VALUES('Dell XPS 13 Laptop', 1199, NOW());

INSERT INTO products (name, price, create_at) VALUES('Nikon D850 DSLR', 2999, NOW());

INSERT INTO products (name, price, create_at) VALUES('Sony PlayStation 5', 499, NOW());

INSERT INTO products (name, price, create_at) VALUES('KitchenAid Stand Mixer', 349, NOW());

INSERT INTO bills (description, observations, client_id, create_at) VALUES('Office equipment invoice', null, 1, NOW());

INSERT INTO bills (description, observations, client_id, create_at) VALUES('Bicycle invoice', null, 1, NOW());


INSERT INTO bill_items (amount, bill_id, product_id) VALUES (1, 1, 1);

INSERT INTO bill_items (amount, bill_id, product_id) VALUES (2, 1, 4);

INSERT INTO bill_items (amount, bill_id, product_id) VALUES (1, 1, 5);

INSERT INTO bill_items (amount, bill_id, product_id) VALUES (1, 1, 7);

INSERT INTO bill_items (amount, bill_id, product_id) VALUES (3, 2, 5);