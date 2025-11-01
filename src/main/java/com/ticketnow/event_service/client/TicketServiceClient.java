package com.ticketnow.event_service.client;

import com.ticketnow.event_service.dto.DisponibilidadeDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TicketServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String TICKET_SERVICE_URL = "http://localhost:8081/ticket_service/disponibilidade";

    public void enviarDisponibilidadeDoEvento(DisponibilidadeDTO dto) {
        HttpEntity<DisponibilidadeDTO> request = new HttpEntity<>(dto);

        ResponseEntity<Void> response = restTemplate.exchange(
                TICKET_SERVICE_URL,
                HttpMethod.POST,
                request,
                Void.class
        );

        System.out.println("DIPOSNIBILIDADE ENVIADA: " + response.getStatusCode());
    }

    public void alterarDisponibilidadeDoEvento(DisponibilidadeDTO dto) {
        HttpEntity<DisponibilidadeDTO> request = new HttpEntity<>(dto);

        ResponseEntity<Void> response = restTemplate.exchange(
                TICKET_SERVICE_URL,
                HttpMethod.PUT,
                request,
                Void.class
        );

        System.out.println("DISPONIBILIDADE ALTERADA: " + response.getStatusCode());
    }

}