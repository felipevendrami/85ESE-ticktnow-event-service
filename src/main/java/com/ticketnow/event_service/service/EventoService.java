package com.ticketnow.event_service.service;

import com.ticketnow.event_service.enums.StatusEvento;
import com.ticketnow.event_service.model.Evento;
import com.ticketnow.event_service.repository.EventoRepository;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento cadastrarEvento(Evento evento) {
        if (evento.getStatus() == null) {
            evento.setStatus(StatusEvento.EM_PAUSA);
        }

        return eventoRepository.save(evento);
    }
}
