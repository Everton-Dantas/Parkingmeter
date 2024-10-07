# Projeto Parquímetro

Este projeto é um sistema de gerenciamento de parquímetro que permite controlar a entrada e saída de veículos, calculando o valor devido pelo tempo estacionado.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.2
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Validation
- H2 Database
- Lombok
- Jakarta Validation e Hibernate Validator
- Spring Boot Starter Test

## Funcionalidades

- Registro de entrada de veículos no estacionamento.
- Registro de saída e cálculo do valor devido.
- Tratamento de erros.

## Pré-requisitos

- Java 17 ou superior instalado.
- Maven configurado.

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Everton-Dantas/Parkingmeter.git
   ```

2. **Compile e construa o projeto:**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesso à API:**
   - Acesse `http://localhost:8080/api/parquimetros` para interagir com os endpoints.

## API Endpoints

- **POST** `/api/parquimetros/entrada`: Registra a entrada de um veículo.
- **POST** `/api/parquimetros/saida/{placa}`: Registra a saída de um veículo pela placa.

## Exemplos de Uso

- **Registrar Entrada**
  ```bash
  curl -X POST http://localhost:8080/api/parquimetros/entrada \
  -H "Content-Type: application/json" \
  -d '{"placa": "ABC1234", "modelo": "Sedan"}'
  ```

- **Registrar Saída**
  ```bash
  curl -X POST http://localhost:8080/api/parquimetros/saida/ABC1234
  ```

## Tratamento de Erros

A aplicação trata exceções comuns através de um manipulador global de exceções, garantindo que mensagens de erro sejam retornadas:

- `ResourceNotFoundException` para tentativas de acesso a tickets inexistentes.
- `IllegalStateException` para violações de lógica de negócio.
- `ValidationException` para falhas de validação de dados de entrada.
