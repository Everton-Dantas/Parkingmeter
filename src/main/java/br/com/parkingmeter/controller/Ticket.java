package br.com.parkingmeter.controller;

import java.time.LocalDateTime;

public class Ticket {
    private String placaVeiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private double valor;

    // Construtor para criar um ticket com a hora de entrada
    public Ticket(String placaVeiculo, LocalDateTime horaEntrada) {
        this.placaVeiculo = placaVeiculo;
        this.horaEntrada = horaEntrada;
        this.horaSaida = null;  // Inicialmente, a saída é null
        this.valor = 0.0;       // Valor inicial é zero
    }

    // Métodos getters e setters
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // Método para calcular a duração de estacionamento (em horas)
    public long calcularDuracaoEmHoras() {
        if (horaSaida == null) {
            return 0;
        }
        return java.time.Duration.between(horaEntrada, horaSaida).toHours();
    }

    // Método para calcular o valor a pagar baseado no tempo
    public void calcularValor(double precoPorHora) {
        long horas = calcularDuracaoEmHoras();
        this.valor = horas * precoPorHora;
    }
}
