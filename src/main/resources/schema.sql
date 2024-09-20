CREATE TABLE veiculos (
    placa VARCHAR(7) PRIMARY KEY,
    modelo VARCHAR(255)
);

CREATE TABLE tickets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_placa VARCHAR(7),
    hora_entrada TIMESTAMP,
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_placa) REFERENCES veiculos(placa)
);
