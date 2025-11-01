package com.ticketnow.event_service.dto;

import com.ticketnow.event_service.enums.StatusEvento;
import lombok.Data;

@Data
public class DisponibilidadeDTO {

    private Long idEvento;
    private Integer qntTotalIngressos;
    private StatusEvento statusEvento;
}
