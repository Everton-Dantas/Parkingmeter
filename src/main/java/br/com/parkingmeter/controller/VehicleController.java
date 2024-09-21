package br.com.parkingmeter.controller;

import br.com.parkingmeter.model.Vehicle;
import br.com.parkingmeter.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Registrar um novo veículo
    @PostMapping("/registrar")
    public ResponseEntity<Vehicle> registrarVeiculo(@RequestBody Vehicle vehicle) {
        Vehicle novoVeiculo = vehicleService.registrarVeiculo(vehicle);
        return ResponseEntity.ok(novoVeiculo);
    }

    // Obter informações de um veículo pela placa
    @GetMapping("/{placa}")
    public ResponseEntity<Vehicle> buscarVeiculo(@PathVariable String placa) {
        Vehicle vehicle = vehicleService.buscarVeiculo(placa);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        }
        return ResponseEntity.notFound().build();
    }

    // Listar todos os veículos cadastrados
    @GetMapping("/todos")
    public ResponseEntity<List<Vehicle>> listarTodosVeiculos() {
        List<Vehicle> todosVeiculos = vehicleService.listarTodosVeiculos();
        return ResponseEntity.ok(todosVeiculos);
    }
}
