DELETE FROM auto;
DELETE FROM users;

INSERT INTO users (username, email, password, created_at) VALUES('juan', 'juan@email.com', '1234', CURRENT_TIMESTAMP);
INSERT INTO users (username, email, password, created_at) VALUES('daniel', 'daniel@email.com', '4321', CURRENT_TIMESTAMP);
INSERT INTO users (username, email, password, created_at) VALUES('maria', 'maria@email.com', '1221', CURRENT_TIMESTAMP);

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'ABC123', id, 'Toyota', 'Corolla', 2020, 'Rojo', CURRENT_TIMESTAMP
FROM users
WHERE username = 'juan';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'BDC123', id, 'Chevrolet', 'Spark', 2015, 'Negro', CURRENT_TIMESTAMP
FROM users
WHERE username = 'juan';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'KAL111', id, 'Toyota', 'Corolla', 2021, 'Negro', CURRENT_TIMESTAMP
FROM users
WHERE username = 'daniel';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'GAS136', id, 'Toyota', '4x4', 2017, 'Rojo', CURRENT_TIMESTAMP
FROM users
WHERE username = 'daniel';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'JUD733', id, 'Nissan', 'GT', 2022, 'Gris', CURRENT_TIMESTAMP
FROM users
WHERE username = 'daniel';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'GVS182', id, 'Mercedes Benz', 'Full', 2025, 'Blanco', CURRENT_TIMESTAMP
FROM users
WHERE username = 'maria';
