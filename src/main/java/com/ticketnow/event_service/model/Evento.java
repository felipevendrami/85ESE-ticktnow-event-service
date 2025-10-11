package com.ticketnow.event_service.model;

import com.ticketnow.event_service.converter.LocalEventoConverter;
import com.ticketnow.event_service.enums.StatusEvento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name= "evento")
@Data
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id;

    @NotBlank(message = "O título do evento é obrigatório.")
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotBlank(message = "A descrição do evento é obrigatória.")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull(message = "As informações do local são obrigatórias")
    @Convert(converter = LocalEventoConverter.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private LocalEvento local;

    @NotNull(message = "A data/hora da realização do evento é obrigatória.")
    @Column(name = "data_hora_realizacao", nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "O preço do evento é obrigatório")
    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private double preco;

    @NotNull(message = "A quantidade de tickets do evento é obrigatória")
    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEvento status;

    @Column(name = "data_hora_criacao", nullable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(name = "data_hora_finalizacao_vendas", nullable = false)
    private LocalDateTime dataHoraFinalizarVendas;

    @Column(name = "id_usuario")
    private int idCriador;

    @Column(name = "imagem")
    private String imagem;

}