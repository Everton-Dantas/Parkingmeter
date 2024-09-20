package br.com.parkingmeter.service;

import br.com.parkingmeter.model.Vehicle;
import br.com.parkingmeter.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Registrar um novo veículo no sistema
    public Vehicle registrarVeiculo(Vehicle vehicle) {
        vehicleRepository.salvarVeiculo(vehicle);
        return vehicle;
    }

    // Buscar informações de um veículo pela placa
    public Vehicle buscarVeiculo(String placa) {
        return vehicleRepository.buscarPorPlaca(placa);
    }

    // Listar todos os veículos registrados no sistema
    public List<Vehicle> listarTodosVeiculos() {
        return vehicleRepository.listarTodosVeiculos();
    }
}
