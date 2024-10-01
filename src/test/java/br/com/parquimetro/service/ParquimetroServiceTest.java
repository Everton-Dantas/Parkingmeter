package br.com.parquimetro.service;

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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        @DisplayName("Deve criar um novo ticket para um veículo válido")
        void testRegistrarEntradaComSucesso() {

            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca("1234567");

            Ticket ticketMock = new Ticket();
            ticketMock.setPlaca("1234567");
            ticketMock.setHoraEntrada(LocalDateTime.now());

            when(ticketRepository.save(any(Ticket.class))).thenReturn(ticketMock);

            Ticket criado = parquimetroService.registrarEntrada(veiculo);

            assertNotNull(criado);
            assertEquals("1234567", criado.getPlaca());
            assertNotNull(criado.getHoraEntrada());
            verify(ticketRepository, times(1)).save(any(Ticket.class));

        }

    }

}