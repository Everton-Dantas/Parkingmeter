package br.com.parquimetro.controller;

import br.com.parquimetro.model.Ticket;
import br.com.parquimetro.model.Veiculo;
import br.com.parquimetro.service.ParquimetroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parquimetros")
@AllArgsConstructor
public class ParquimetroController {

    private final ParquimetroService parquimetroService;

    @PostMapping("/entrada")
    public ResponseEntity<Ticket> registrarEntrada(@Validated @RequestBody Veiculo veiculo) {

        Ticket ticket = parquimetroService.registrarEntrada(veiculo);
        return ResponseEntity.ok(ticket);

    }

    @PostMapping("/saida/{placa}")
    public ResponseEntity<Ticket> registrarSaida(@PathVariable String placa) {

        Ticket ticket = parquimetroService.registrarSaida(placa);
        return ResponseEntity.ok(ticket);

    }

}
