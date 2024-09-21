package br.com.parkingmeter.controller;

import br.com.parkingmeter.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleService {

    private List<Vehicle> veiculos = new ArrayList<>();

    // Adicionar um novo veículo
    public String adicionarVeiculo(Vehicle vehicle) {
        veiculos.add(vehicle);
        return "Veículo adicionado com sucesso!";
    }

    // Remover um veículo pela placa
    public String removerVeiculo(String placa) {
        Optional<Vehicle> veiculoEncontrado = veiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst();

        if (veiculoEncontrado.isPresent()) {
            veiculos.remove(veiculoEncontrado.get());
            return "Veículo removido com sucesso!";
        } else {
            return "Veículo não encontrado!";
        }
    }

    // Buscar um veículo pela placa
    public Vehicle buscarVeiculo(String placa) {
        return veiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst()
                .orElse(null);
    }

    // Listar todos os veículos
    public List<Vehicle> listarTodosVeiculos() {
        return veiculos;
    }

    // Verificar se o veículo está estacionado
    public boolean veiculoEstacionado(String placa) {
        return veiculos.stream()
                .anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa));
    }
}

