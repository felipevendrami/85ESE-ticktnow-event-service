package com.ticketnow.event_service.service;

import com.ticketnow.event_service.repository.EventoRepository;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

}
