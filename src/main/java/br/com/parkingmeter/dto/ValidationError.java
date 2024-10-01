package br.com.parkingmeter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ValidationError {

    private String campo;
    private String descricao;

}
