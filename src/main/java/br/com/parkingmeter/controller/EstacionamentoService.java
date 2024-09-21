package br.com.parkingmeter.controller;

import br.com.parkingmeter.model.Ticket;
import br.com.parkingmeter.model.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoService {
    private List<Vehicle> veiculosEstacionados = new ArrayList<>();
    private int capacidadeMaxima;

    // Construtor para definir a capacidade máxima do estacionamento
    public EstacionamentoService(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    // Método para registrar um veículo no estacionamento
    public String registrarVeiculo(Vehicle veiculo) {
        if (veiculosEstacionados.size() >= capacidadeMaxima) {
            return "Estacionamento lotado. Não é possível registrar mais veículos.";
        }
        veiculosEstacionados.add(veiculo);
        return "Veículo registrado com sucesso!";
    }

    // Método para remover um veículo do estacionamento pela placa
    public String removerVeiculo(String placa) {
        for (Vehicle veiculo : veiculosEstacionados) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculosEstacionados.remove(veiculo);
                return "Veículo removido com sucesso!";
            }
        }
        return "Veículo não encontrado.";
    }

    // Método para listar todos os veículos estacionados
    public List<Vehicle> listarVeiculosEstacionados() {
        return veiculosEstacionados;
    }

    // Método para verificar se há vagas disponíveis
    public String verificarDisponibilidade() {
        int vagasDisponiveis = capacidadeMaxima - veiculosEstacionados.size();
        return vagasDisponiveis > 0 ? "Há " + vagasDisponiveis + " vagas disponíveis." : "Estacionamento lotado.";
    }

    // Registrar entrada e gerar ticket
    public Ticket registrarEntrada(Vehicle vehicle) {
        if (veiculosEstacionados.size() >= capacidadeMaxima) {
            return null; // Estacionamento lotado
        }
        veiculosEstacionados.add(vehicle);

        // Criar e retornar o ticket
        Ticket ticket = new Ticket();
        ticket.setPlaca(vehicle.getPlaca());
        ticket.setHoraEntrada(LocalDateTime.now());
        return ticket;
    }

    // Registrar saída e calcular o valor a pagar
    public Double registrarSaida(String placa) {
        Vehicle veiculoRemover = null;
        for (Vehicle veiculo : veiculosEstacionados) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculoRemover = veiculo;
                break;
            }
        }

        if (veiculoRemover == null) {
            return null; // Veículo não encontrado
        }

        veiculosEstacionados.remove(veiculoRemover);

        // Suponha que existe um ticket associado ao veículo
        Ticket ticket = new Ticket() /* lógica para buscar o ticket */;
        LocalDateTime horaSaida = LocalDateTime.now();
        long horasEstacionado = Duration.between(ticket.getHoraEntrada(), horaSaida).toHours();

        // Calcular valor a pagar (exemplo: R$ 10 por hora)
        Double valorAPagar = horasEstacionado * 10.0;
        return valorAPagar;
    }

    // Calcular tempo estacionado
    public Long calcularTempoEstacionado(String placa) {
        for (Vehicle veiculo : veiculosEstacionados) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                Ticket ticket = new Ticket() /* lógica para buscar o ticket */;
                LocalDateTime horaAtual = LocalDateTime.now();
                return Duration.between(ticket.getHoraEntrada(), horaAtual).toMinutes();
            }
        }
        return null; // Veículo não encontrado
    }
}
