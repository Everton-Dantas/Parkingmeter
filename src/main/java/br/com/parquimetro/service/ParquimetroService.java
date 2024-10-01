package br.com.parquimetro.service;

import br.com.parquimetro.exception.ResourceNotFoundException;
import br.com.parquimetro.model.Ticket;
import br.com.parquimetro.model.Veiculo;
import br.com.parquimetro.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParquimetroService {

    private final TicketRepository ticketRepository;

    public Ticket registrarEntrada(Veiculo veiculo) {

        boolean existeTicketAtivo = ticketRepository.findByPlaca(veiculo.getPlaca()).stream()
                .anyMatch(ticket -> ticket.getHoraSaida() == null);

        if (existeTicketAtivo) {
            throw new IllegalStateException("Já existe um ticket ativo para o veículo com placa: " + veiculo.getPlaca());
        }

        Ticket ticket = new Ticket();
        ticket.setPlaca(veiculo.getPlaca());
        ticket.setHoraEntrada(LocalDateTime.now());
        return ticketRepository.save(ticket);

    }

    @Transactional
    public Ticket registrarSaida(String placa) {

        BigDecimal valorPorHora = BigDecimal.valueOf(5.00);

        Optional<Ticket> optionalTicket = ticketRepository.findByPlaca(placa).stream()
                .filter(ticket -> ticket.getHoraSaida() == null)
                .findFirst();

        if(optionalTicket.isPresent()) {

            Ticket ticket = optionalTicket.get();

            ticket.setHoraSaida(LocalDateTime.now());

            long horasEstacionadas = java.time.Duration.between(ticket.getHoraEntrada(), ticket.getHoraSaida()).toHours();

            if (horasEstacionadas == 0) {
                ticket.setValor(valorPorHora);
            } else {
                ticket.setValor(BigDecimal.valueOf(horasEstacionadas).multiply(valorPorHora).setScale(2, RoundingMode.HALF_UP));
            }

            return ticketRepository.save(ticket);

        } else {

            throw new ResourceNotFoundException("Ticket não encontrado ou já fechado para a placa: " + placa);

        }
    }
}
