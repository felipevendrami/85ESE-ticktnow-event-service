package com.ticketnow.event_service.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketnow.event_service.model.LocalEvento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LocalEventoConverter implements AttributeConverter<LocalEvento, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(LocalEvento localEvento) {
        try{
            return objectMapper.writeValueAsString(localEvento);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter LocalEvento para JSON", e);
        }
    }

    @Override
    public LocalEvento convertToEntityAttribute(String json) {
        try{
            return objectMapper.readValue(json, LocalEvento.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON para LocalEvento", e);
        }
    }
}