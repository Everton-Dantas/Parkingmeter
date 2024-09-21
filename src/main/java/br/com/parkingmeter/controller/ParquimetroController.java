package br.com.parkingmeter.controller;

import br.com.parkingmeter.model.Ticket;
import br.com.parkingmeter.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parquimetro")
public class ParquimetroController<Vehicle extends br.com.parkingmeter.model.Vehicle> {

    @Autowired
    private EstacionamentoService estacionamentoService;

    // Registrar a entrada de um veículo
    @PostMapping("/entrada")
    public ResponseEntity<Ticket> registrarEntrada(@RequestBody Vehicle vehicle) {
        Ticket ticket = estacionamentoService.registrarEntrada(vehicle);
        return ResponseEntity.ok(ticket);
    }

    // Registrar a saída de um veículo e calcular o valor a pagar
    @PostMapping("/saida/{placa}")
    public ResponseEntity<Double> registrarSaida(@PathVariable String placa) {
        Double valorAPagar = estacionamentoService.registrarSaida(placa);
        if (valorAPagar != null) {
            return ResponseEntity.ok(valorAPagar);
        }
        return ResponseEntity.badRequest().body(null); // Se não encontrar o veículo
    }

    // Ver o tempo de estacionamento de um veículo
    @GetMapping("/tempo/{placa}")
    public ResponseEntity<Long> verificarTempo(@PathVariable String placa) {
        Long tempoEstacionado = estacionamentoService.calcularTempoEstacionado(placa);
        if (tempoEstacionado != null) {
            return ResponseEntity.ok(tempoEstacionado);
        }
        return ResponseEntity.notFound().build();
    }

    // Ver todos os veículos atualmente estacionados
    @GetMapping("/veiculos")
    public ResponseEntity<List<Vehicle>> listarVeiculos() {
        List<Vehicle> veiculosEstacionados = (List<Vehicle>) estacionamentoService.listarVeiculosEstacionados();
        return ResponseEntity.ok(veiculosEstacionados);
    }
}
