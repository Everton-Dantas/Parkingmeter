package br.com.parkingmeter.model;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private List<Ticket> tickets;

    // Construtor
    public Estacionamento() {
        this.tickets = new ArrayList<>();
    }

    // Adicionar um ticket ao estacionamento
    public void adicionarTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    // Remover um ticket ao registrar a saída do veículo
    public void removerTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    // Listar todos os veículos atualmente estacionados
    public List<Vehicle> listarVeiculosEstacionados() {
        List<Vehicle> veiculos = new ArrayList<>();
        for (Ticket ticket : tickets) {
            veiculos.add(ticket.getVehicle());
        }
        return veiculos;
    }

    // Buscar um ticket pelo número da placa
    public Ticket buscarTicketPorPlaca(String placa) {
        for (Ticket ticket : tickets) {
            if (ticket.getVehicle().getPlaca().equals(placa)) {
                return ticket;
            }
        }
        return null;
    }
}
