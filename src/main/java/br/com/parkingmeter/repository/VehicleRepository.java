package br.com.parkingmeter.repository;

import br.com.parkingmeter.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepository {

    private List<Vehicle> veiculosCadastrados = new ArrayList<>();

    // Salvar um novo veículo no sistema
    public void salvarVeiculo(Vehicle vehicle) {
        veiculosCadastrados.add(vehicle);
    }

    // Buscar um veículo pela placa
    public Vehicle buscarPorPlaca(String placa) {
        for (Vehicle vehicle : veiculosCadastrados) {
            if (vehicle.getPlaca().equals(placa)) {
                return vehicle;
            }
        }
        return null;
    }

    // Listar todos os veículos cadastrados no sistema
    public List<Vehicle> listarTodosVeiculos() {
        return new ArrayList<>(veiculosCadastrados); // Retorna uma cópia da lista para evitar modificações externas
    }
}
