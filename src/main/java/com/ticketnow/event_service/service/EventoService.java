package com.ticketnow.event_service.service;

import com.ticketnow.event_service.client.TicketServiceClient;
import com.ticketnow.event_service.dto.DisponibilidadeDTO;
import com.ticketnow.event_service.dto.StatusEventoDTO;
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
    private final TicketServiceClient ticketServiceClient;
    private Evento evento;

    public EventoService(EventoRepository eventoRepository, TicketServiceClient ticketServiceClient) {
        this.eventoRepository = eventoRepository;
        this.ticketServiceClient = ticketServiceClient;
    }

    public Evento cadastrarEvento(Evento novoEvento) {
        evento = novoEvento;

        validaRegradeDatas();
        validaRegradeStatus();

        eventoRepository.save(novoEvento);

        ticketServiceClient.enviarDisponibilidadeDoEvento(geracaoDisponibilidadeDTO());

        return novoEvento;
    }

    public List<Evento> listarTodosEventos() {
        return eventoRepository.findAll();
    }

    public Evento buscarEventoPorId(Long idEvento) {
        return eventoRepository.findById(idEvento)
                .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("O evento com o ID %d não foi encontrado", idEvento)));
    }

    public Evento alterarEvento(Evento eventoAtualizado, Long idEvento) {
        evento = eventoRepository.findById(idEvento)
               .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("O evento com o ID %d não foi encontrado", idEvento)));

        evento.setTitulo(eventoAtualizado.getTitulo());
        evento.setDescricao(eventoAtualizado.getDescricao());
        evento.setLocal(eventoAtualizado.getLocal());
        evento.setDataHora(eventoAtualizado.getDataHora());
        evento.setPreco(eventoAtualizado.getPreco());
        evento.setQuantidade(eventoAtualizado.getQuantidade());
        evento.setDataHoraFinalizarVendas(eventoAtualizado.getDataHoraFinalizarVendas());
        evento.setImagem(eventoAtualizado.getImagem());
        evento.setStatus(eventoAtualizado.getStatus());

        validaRegradeDatas();
        validaRegradeStatus();
        ticketServiceClient.alterarDisponibilidadeDoEvento(geracaoDisponibilidadeDTO());

        return eventoRepository.save(evento);
    }

    public Evento alterarStatusEvento(Long idEvento, StatusEventoDTO statusEventoDTO) {
        evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("O evento com o ID %d não foi encontrado", idEvento)));

        evento.setStatus(statusEventoDTO.getStatusEvento());
        return eventoRepository.save(evento);
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

    private DisponibilidadeDTO geracaoDisponibilidadeDTO(){
        DisponibilidadeDTO disponibilidadeEventoDTO = new DisponibilidadeDTO();
        disponibilidadeEventoDTO.setIdEvento(evento.getId());
        disponibilidadeEventoDTO.setQntTotalIngressos(evento.getQuantidade());
        disponibilidadeEventoDTO.setStatusEvento(evento.getStatus());

        return disponibilidadeEventoDTO;
    }

}