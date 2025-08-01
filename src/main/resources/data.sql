DELETE FROM auto;
DELETE FROM users;

INSERT INTO users (username, email, password, created_at) VALUES('admin', 'admin@email.com', '$2a$10$QsGjxOoH2Rs3uziTC9peS.s7wbReyDwPnPeGtpxqIukymKx7.baI.', CURRENT_TIMESTAMP);
INSERT INTO users (username, email, password, created_at) VALUES('juan', 'juan@email.com', '$2a$10$QsGjxOoH2Rs3uziTC9peS.s7wbReyDwPnPeGtpxqIukymKx7.baI.', CURRENT_TIMESTAMP);
INSERT INTO users (username, email, password, created_at) VALUES('daniel', 'daniel@email.com', '$2a$10$rIfmk6nrJXInELSUehB1R.g7anD4hcqsFa1acyUEF6liRhc6oV9nq', CURRENT_TIMESTAMP);
INSERT INTO users (username, email, password, created_at) VALUES('maria', 'maria@email.com', '$2a$10$yM/.XB7DUvWSi5b.6KpoxOI3cNiC0QrRfmq3qJDJ9JUMYT.y.kc6O', CURRENT_TIMESTAMP);

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
