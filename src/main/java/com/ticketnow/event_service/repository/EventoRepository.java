package com.ticketnow.event_service.repository;

import com.ticketnow.event_service.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {}
