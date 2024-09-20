package br.com.parkingmeter.model;

import java.time.LocalDateTime;

public class Ticket {

    private Vehicle vehicle;
    private LocalDateTime horaEntrada;

    // Construtor padrão
    public Ticket() {
    }

    // Construtor com argumentos
    public Ticket(Vehicle vehicle, LocalDateTime horaEntrada) {
        this.vehicle = vehicle;
        this.horaEntrada = horaEntrada;
    }

    // Getters e Setters
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    // Método toString para depuração
    @Override
    public String toString() {
        return "Ticket{" +
                "vehicle=" + vehicle +
                ", horaEntrada=" + horaEntrada +
                '}';
    }
}
