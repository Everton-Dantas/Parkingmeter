package br.com.parkingmeter.model;

public class Vehicle {

    private String placa;
    private String modelo;

    // Construtor padrão
    public Vehicle() {
    }

    // Construtor com argumentos
    public Vehicle(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    // Getters e Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Método toString para depuração
    @Override
    public String toString() {
        return "Vehicle{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
