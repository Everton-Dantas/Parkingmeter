package br.com.parquimetro.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class Veiculo {

    @NotBlank(message = "A placa é obrigatória.")
    @Size(min = 7, max = 7, message = "A placa deve ter exatamente 7 caracteres.")
    private String placa;

    @NotBlank(message = "O modelo é obrigatório.")
    private String modelo;

}
