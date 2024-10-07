package br.com.parquimetro.controller;

import br.com.parquimetro.exception.ResourceNotFoundException;
import br.com.parquimetro.model.Ticket;
import br.com.parquimetro.model.Veiculo;
import br.com.parquimetro.service.ParquimetroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParquimetroController.class)
class ParquimetroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParquimetroService parquimetroService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Nested
    @DisplayName("Testes para registrar entrada")
    class RegistrarEntradaTests {

        @Test
        @DisplayName("Deve registrar a entrada com sucesso")
        void registrarEntradaComSucesso() throws Exception {
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca("XYZ5678");
            veiculo.setModelo("Camaro");

            Ticket ticket = new Ticket();
            ticket.setPlaca("XYZ5678");
            ticket.setHoraEntrada(LocalDateTime.now());

            when(parquimetroService.registrarEntrada(any(Veiculo.class))).thenReturn(ticket);

            mockMvc.perform(post("/api/parquimetros/entrada")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(veiculo)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.placa").value("XYZ5678"))
                    .andExpect(jsonPath("$.horaEntrada").isNotEmpty());
        }

        @Test
        @DisplayName("Deve retornar erro de validação para campos inválidos")
        void registrarEntradaDadosInvalidos() throws Exception {
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca("");

            mockMvc.perform(post("/api/parquimetros/entrada")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(veiculo)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Testes para registrar saída")
    class RegistrarSaidaTests {

        @Test
        @DisplayName("Deve registrar a saída com sucesso")
        void registrarSaidaComSucesso() throws Exception {
            Ticket ticket = new Ticket();
            ticket.setPlaca("XYZ5678");
            ticket.setHoraEntrada(LocalDateTime.now().minusHours(2));
            ticket.setHoraSaida(LocalDateTime.now());
            ticket.setValor(BigDecimal.valueOf(10.00));

            when(parquimetroService.registrarSaida("XYZ5678")).thenReturn(ticket);

            mockMvc.perform(post("/api/parquimetros/saida/XYZ5678")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.placa").value("XYZ5678"))
                    .andExpect(jsonPath("$.horaSaida").isNotEmpty())
                    .andExpect(jsonPath("$.valor").value(10.00));
        }

        @Test
        @DisplayName("Deve retornar erro quando ticket não encontrado")
        void registrarSaidaTicketNaoEncontrado() throws Exception {
            when(parquimetroService.registrarSaida("ABC1234"))
                    .thenThrow(new ResourceNotFoundException("Ticket não encontrado ou já fechado para a placa: ABC1234"));

            mockMvc.perform(post("/api/parquimetros/saida/ABC1234")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.campo").value("ticket"))
                    .andExpect(jsonPath("$.descricao").value("Ticket não encontrado ou já fechado para a placa: ABC1234"));
        }
    }
}
