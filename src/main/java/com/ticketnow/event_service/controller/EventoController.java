package com.ticketnow.event_service.controller;


import com.ticketnow.event_service.service.EventoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticketnow_api/evento")
public class EventoController {

    private EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

}
