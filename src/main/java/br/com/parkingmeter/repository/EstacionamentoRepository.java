package br.com.parkingmeter.repository;

import br.com.parkingmeter.model.Ticket;
import br.com.parkingmeter.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EstacionamentoRepository {

    private List<Ticket> ticketsEstacionados = new ArrayList<>();

    // Salvar um novo ticket (entrada de um veículo)
    public void salvarTicket(Ticket ticket) {
        ticketsEstacionados.add(ticket);
    }

    // Remover um ticket (saída de um veículo)
    public void removerTicket(Ticket ticket) {
        ticketsEstacionados.remove(ticket);
    }

    // Buscar um ticket pelo número da placa
    public Ticket buscarTicketPorPlaca(String placa) {
        for (Ticket ticket : ticketsEstacionados) {
            if (ticket.getVehicle().getPlaca().equals(placa)) {
                return ticket;
            }
        }
        return null;
    }

    // Listar todos os veículos que estão atualmente estacionados
    public List<Vehicle> listarVeiculosEstacionados() {
        List<Vehicle> veiculos = new ArrayList<>();
        for (Ticket ticket : ticketsEstacionados) {
            veiculos.add(ticket.getVehicle());
        }
        return veiculos;
    }
}
