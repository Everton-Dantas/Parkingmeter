-- Inserir veículos
INSERT INTO veiculos (placa, modelo) VALUES ('ABC1234', 'Toyota Corolla');
INSERT INTO veiculos (placa, modelo) VALUES ('XYZ5678', 'Honda Civic');

-- Inserir tickets (com base nos veículos inseridos)
INSERT INTO tickets (vehicle_placa, hora_entrada) VALUES ('ABC1234', '2024-09-19T08:00:00');
INSERT INTO tickets (vehicle_placa, hora_entrada) VALUES ('XYZ5678', '2024-09-19T09:30:00');
