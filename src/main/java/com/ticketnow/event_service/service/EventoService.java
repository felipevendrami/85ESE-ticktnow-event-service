package com.ticketnow.event_service.service;

import com.ticketnow.event_service.enums.StatusEvento;
import com.ticketnow.event_service.exception.RecursoNaoEncontradoException;
import com.ticketnow.event_service.model.Evento;
import com.ticketnow.event_service.repository.EventoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private Evento evento;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento cadastrarEvento(Evento novoEvento) {
        evento = novoEvento;

        validaRegradeDatas();
        validaRegradeStatus();

        return eventoRepository.save(novoEvento);
    }

    public List<Evento> listarTodosEventos() {
        return eventoRepository.findAll();
    }

    public Evento buscarEventoPorId(Long idEvento) {
        return eventoRepository.findById(idEvento)
                .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("O evento com o ID %d não foi encontrado", idEvento)));
    }

    public Evento alterarEvento(Evento eventoAtualizado, Long idEvento) {
        Evento eventoExistente = eventoRepository.findById(idEvento)
               .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("O evento com o ID %d não foi encontrado", idEvento)));

        eventoExistente.setTitulo(eventoAtualizado.getTitulo());
        eventoExistente.setDescricao(eventoAtualizado.getDescricao());
        eventoExistente.setLocal(eventoAtualizado.getLocal());
        eventoExistente.setDataHora(eventoAtualizado.getDataHora());
        eventoExistente.setPreco(eventoAtualizado.getPreco());
        eventoExistente.setQuantidade(eventoAtualizado.getQuantidade());
        eventoExistente.setDataHoraFinalizarVendas(eventoAtualizado.getDataHoraFinalizarVendas());
        eventoExistente.setImagem(eventoAtualizado.getImagem());

        evento = eventoExistente;

        validaRegradeDatas();
        validaRegradeStatus();

        return eventoRepository.save(eventoExistente);
    }

    private void validaRegradeStatus() {
        if (evento.getStatus() == null) {
            evento.setStatus(StatusEvento.EM_PAUSA);
        }
    }

    private void validaRegradeDatas() {
        if (evento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de realização do evento não pode ser anterior à data atual.");
        }

        if (evento.getDataHoraFinalizarVendas().isAfter(evento.getDataHora())) {
            throw new IllegalArgumentException("A data de finalizaçao de vendas deve ser posterior a data da realizaçaõ do evento.");
        }

        if (evento.getDataHoraCriacao() == null) {
            evento.setDataHoraCriacao(LocalDateTime.now());
        }
    }

}
