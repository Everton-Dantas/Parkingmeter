package br.com.parkingmeter.controller;

import br.com.parkingmeter.model.Ticket;
import br.com.parkingmeter.model.Veiculo;
import br.com.parkingmeter.service.ParquimetroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parquimetros")
@AllArgsConstructor
public class ParquimetroController {

    private final ParquimetroService parquimetroService;

    @PostMapping("/entrada")
    public ResponseEntity<Ticket> registrarEntrada(@RequestBody Veiculo veiculo) {

        Ticket ticket = parquimetroService.registrarEntrada(veiculo);
        return ResponseEntity.ok(ticket);

    }

    @PostMapping("/saida/{placa}")
    public ResponseEntity<Ticket> registrarSaida(@PathVariable String placa) {

        Ticket ticket = parquimetroService.registrarSaida(placa);
        return ResponseEntity.ok(ticket);

    }

}
