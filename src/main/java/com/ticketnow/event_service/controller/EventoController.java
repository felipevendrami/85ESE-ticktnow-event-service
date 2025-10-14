package com.ticketnow.event_service.controller;


import com.ticketnow.event_service.model.Evento;
import com.ticketnow.event_service.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketnow_api/eventos")
public class EventoController {

    private EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Cadastrar evento
    @PostMapping
    public Evento cadastrarEvento(@RequestBody Evento evento){
        return eventoService.cadastrarEvento(evento);
    }

    // List todos os eventos
    @GetMapping
    public List<Evento> listarTodosEventos(){
        return null;
    }

    // Get evento por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEventoPorId(@PathVariable int idEvento){
        return null;
    }

    // Alterar evento
    @PutMapping("/{id}")
    public Evento atualizarEvento(@RequestBody Evento evento, @PathVariable Long idEvento){
        return null;
    }

    // Deletar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long idEvento){
        return null;
    }

    // Alterar status do evento
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> alterarStatusEvento(@PathVariable Long idEvento, @RequestBody Evento evento){
        return null;
    }

}
