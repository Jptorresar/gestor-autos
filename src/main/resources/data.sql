-- Eliminar datos previos
DELETE FROM auto;
DELETE FROM users;

-- Insertar usuarios (contraseñas ya encriptadas con bcrypt)
INSERT INTO users (username, email, password, created_at) VALUES('admin', 'admin@email.com', '$2a$10$QsGjxOoH2Rs3uziTC9peS.s7wbReyDwPnPeGtpxqIukymKx7.baI.', CURRENT_TIMESTAMP); /*admin:1234*/
INSERT INTO users (username, email, password, created_at) VALUES('juan', 'juan@email.com', '$2a$10$QsGjxOoH2Rs3uziTC9peS.s7wbReyDwPnPeGtpxqIukymKx7.baI.', CURRENT_TIMESTAMP); /*juan:1234*/
INSERT INTO users (username, email, password, created_at) VALUES('daniel', 'daniel@email.com', '$2a$10$rIfmk6nrJXInELSUehB1R.g7anD4hcqsFa1acyUEF6liRhc6oV9nq', CURRENT_TIMESTAMP); /*daniel:4321*/
INSERT INTO users (username, email, password, created_at) VALUES('maria', 'maria@email.com', '$2a$10$yM/.XB7DUvWSi5b.6KpoxOI3cNiC0QrRfmq3qJDJ9JUMYT.y.kc6O', CURRENT_TIMESTAMP); /*maria:1221*/
INSERT INTO users (username, email, password, created_at) VALUES('sofia', 'sofia@email.com', '$2a$10$QsGjxOoH2Rs3uziTC9peS.s7wbReyDwPnPeGtpxqIukymKx7.baI.', CURRENT_TIMESTAMP); /*sofia:1234*/
INSERT INTO users (username, email, password, created_at) VALUES('pedro', 'pedro@email.com', '$2a$10$rIfmk6nrJXInELSUehB1R.g7anD4hcqsFa1acyUEF6liRhc6oV9nq', CURRENT_TIMESTAMP); /*pedro:4321*/

-- Insertar autos asociados a cada usuario
-- Juan
INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'ABC123', id, 'Toyota', 'corolla', 2020, 'Rojo', CURRENT_TIMESTAMP FROM users WHERE username = 'juan';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'BDC123', id, 'Chevrolet', 'spark', 2015, 'Negro', CURRENT_TIMESTAMP FROM users WHERE username = 'juan';

-- Daniel
INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'KAL111', id, 'Toyota', 'corolla', 2021, 'Negro', CURRENT_TIMESTAMP FROM users WHERE username = 'daniel';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'GAS136', id, 'Toyota', '4x4', 2017, 'Rojo', CURRENT_TIMESTAMP FROM users WHERE username = 'daniel';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'JUD733', id, 'Nissan', 'gt', 2022, 'Gris', CURRENT_TIMESTAMP FROM users WHERE username = 'daniel';

-- Maria
INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'GVS182', id, 'Mercedes Benz', 'full', 2025, 'Blanco', CURRENT_TIMESTAMP FROM users WHERE username = 'maria';

-- Sofia
INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'SFS901', id, 'Hyundai', 'tucson', 2023, 'Azul', CURRENT_TIMESTAMP FROM users WHERE username = 'sofia';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'SSF902', id, 'Kia', 'sportage', 2022, 'Gris', CURRENT_TIMESTAMP FROM users WHERE username = 'sofia';

-- Pedro
INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'PD1234', id, 'Mazda', '3', 2019, 'Rojo', CURRENT_TIMESTAMP FROM users WHERE username = 'pedro';

INSERT INTO auto (placa, user_id, marca, modelo, year, color, created_at)
SELECT 'PD5678', id, 'Ford', 'escape', 2020, 'Negro', CURRENT_TIMESTAMP FROM users WHERE username = 'pedro';
