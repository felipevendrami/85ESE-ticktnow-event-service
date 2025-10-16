package com.ticketnow.event_service.controller;


import com.ticketnow.event_service.model.Evento;
import com.ticketnow.event_service.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketnow_api/eventos")
public class EventoController {

    private final EventoService eventoService;

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
        return eventoService.listarTodosEventos();
    }

    // Get evento por Id
    @GetMapping("/{idEvento}")
    public ResponseEntity<?> buscarEventoPorId(@PathVariable Long idEvento){
        Evento evento = eventoService.buscarEventoPorId(idEvento);
        return ResponseEntity.ok(evento);
    }

    // Alterar evento
    @PutMapping("/{idEvento}")
    public ResponseEntity<?> atualizarEvento(@RequestBody @Valid Evento eventoAtualizado, @PathVariable Long idEvento){
        Evento ev = eventoService.alterarEvento(eventoAtualizado, idEvento);
        return ResponseEntity.ok(ev);
    }

    // Deletar evento
    @DeleteMapping("/{idEvento}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long idEvento){
        return null;
    }

    // Alterar status do evento
    @PatchMapping("/{idEvento}/status")
    public ResponseEntity<?> alterarStatusEvento(@PathVariable Long idEvento, @RequestBody Evento evento){
        return null;    }

}
