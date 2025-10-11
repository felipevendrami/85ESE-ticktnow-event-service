package com.ticketnow.event_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalEvento {

    private String nome;
    private String endereco;
    private String bairro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String maps;

}