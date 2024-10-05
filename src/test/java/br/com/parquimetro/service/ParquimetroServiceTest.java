package br.com.parquimetro.service;

import br.com.parquimetro.exception.ResourceNotFoundException;
import br.com.parquimetro.model.Ticket;
import br.com.parquimetro.model.Veiculo;
import br.com.parquimetro.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ParquimetroServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private ParquimetroService parquimetroService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    @Nested
    @DisplayName("Ao registrar entrada")
    class quandoRegistrarEntrada {

        @Test
        @DisplayName("Deve criar um novo ticket para o veículo válido informado")
        void testRegistrarEntradaComSucesso() {

            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca("1234567");

            Ticket ticket = new Ticket();
            ticket.setPlaca("1234567");
            ticket.setHoraEntrada(LocalDateTime.now());

            when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

            Ticket criado = parquimetroService.registrarEntrada(veiculo);

            assertNotNull(criado);
            assertEquals("1234567", criado.getPlaca());
            assertNotNull(criado.getHoraEntrada());
            verify(ticketRepository, times(1)).save(any(Ticket.class));

        }

        @Test
        @DisplayName("Deve tratar corretamente quando o ticket ainda está ativo")
        void testRegistrarEntradaComTicketAtivo() {

            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca("ABC1234");

            Ticket ticketAtivo = new Ticket();
            ticketAtivo.setHoraEntrada(LocalDateTime.now());
            ticketAtivo.setHoraSaida(null);

            when(ticketRepository.findByPlaca("ABC1234")).thenReturn(Collections.singletonList(ticketAtivo));

            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
                parquimetroService.registrarEntrada(veiculo);
            });
            
            assertEquals("Já existe um ticket ativo para o veículo com placa: ABC1234", exception.getMessage());

        }

    }

    @Nested
    @DisplayName("Ao registrar saída")
    class quandoRegistrarSaida {

        @Test
        @DisplayName("Deve fechar ticket existente para o veículo válido informado")
        void testRegistrarSaidaComSucesso() {

            Ticket ticket = new Ticket();
            ticket.setPlaca("1234567");
            ticket.setHoraEntrada(LocalDateTime.now().minusHours(2));
            ticket.setHoraSaida(null);

            BigDecimal valorPorHora = BigDecimal.valueOf(5.00);
            BigDecimal valorEsperado = BigDecimal.valueOf(2).multiply(valorPorHora).setScale(2, RoundingMode.HALF_UP);

            when(ticketRepository.findByPlaca("1234567")).thenReturn(Collections.singletonList(ticket));
            when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

            Ticket resultado = parquimetroService.registrarSaida("1234567");

            assertNotNull(resultado.getHoraSaida());
            assertEquals(valorEsperado, resultado.getValor());
            verify(ticketRepository, times(1)).save(any(Ticket.class));

        }

        @Test
        @DisplayName("Deve tratar corretamente ao não encontrar ticket")
        void testRegistrarSaidaTicketNaoEncontrado() {

            when(ticketRepository.findByPlaca("ABC1234")).thenReturn(Collections.emptyList());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
                parquimetroService.registrarSaida("ABC1234");
            });

            assertEquals("Ticket não encontrado ou já fechado para a placa: ABC1234", exception.getMessage());

        }

        @Test
        @DisplayName("Deve tratar corretamente quando ticket já está fechado")
        void testRegistrarSaidaTicketFechado() {

            Ticket ticket = new Ticket();
            ticket.setPlaca("1234567");
            ticket.setHoraEntrada(LocalDateTime.now().minusHours(2));
            ticket.setHoraSaida(LocalDateTime.now().minusHours(1));

            when(ticketRepository.findByPlaca("1234567")).thenReturn(Collections.singletonList(ticket));

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
                parquimetroService.registrarSaida("1234567");
            });

            assertEquals("Ticket não encontrado ou já fechado para a placa: 1234567", exception.getMessage());

        }

    }

}