package br.com.parkingmeter.service;

import br.com.parkingmeter.model.Ticket;
import br.com.parkingmeter.model.Vehicle;
import br.com.parkingmeter.repository.EstacionamentoRepository;
import br.com.parkingmeter.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private VehicleService vehicleService;

    // Registrar a entrada de um veículo no estacionamento
    public Ticket registrarEntrada(Vehicle vehicle) {
        Object DateTimeUtils = new Object();
        Ticket ticket = new Ticket(vehicle, DateTimeUtils.getCurrentDateTime());
        estacionamentoRepository.salvarTicket(ticket);
        return ticket;
    }

    // Registrar a saída do veículo e calcular o valor a pagar
    public Double registrarSaida(String placa) {
        Ticket ticket = estacionamentoRepository.buscarTicketPorPlaca(placa);
        if (ticket != null) {
            long tempoEstacionadoEmMinutos = calcularTempoEstacionado(ticket.getVehicle().getPlaca());
            Double valorAPagar = calcularValorAPagar(tempoEstacionadoEmMinutos);
            estacionamentoRepository.removerTicket(ticket); // Remove o ticket do sistema após o pagamento
            return valorAPagar;
        }
        return null; // Retorna nulo se o veículo não for encontrado
    }

    // Calcular o tempo de estacionamento em minutos para um veículo específico
    public Long calcularTempoEstacionado(String placa) {
        Ticket ticket = estacionamentoRepository.buscarTicketPorPlaca(placa);
        if (ticket != null) {
            Duration duracao = Duration.between(ticket.getHoraEntrada(), DateTimeUtils.getCurrentDateTime());
            return duracao.toMinutes();
        }
        return null;
    }

    // Listar todos os veículos que estão atualmente estacionados
    public List<Vehicle> listarVeiculosEstacionados() {
        return estacionamentoRepository.listarVeiculosEstacionados();
    }

    // Método auxiliar para calcular o valor a pagar
    private Double calcularValorAPagar(long tempoEmMinutos) {
        final double precoPorMinuto = 0.05; // Exemplo de preço por minuto
        return tempoEmMinutos * precoPorMinuto;
    }
}

}
