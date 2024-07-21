package com.microservice.turnos.mapper;

import com.microservice.turnos.controller.dto.TurnosDto;
import com.microservice.turnos.entities.Turnos;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TurnosMapper {

    public TurnosDto toDto(Turnos turnos) {
        if (turnos == null) {
            return null;
        }

        return TurnosDto.builder()
                .id(turnos.getId())
                .name(turnos.getName())
                .pacienteId(turnos.getPacienteId())
                .profesionalId(turnos.getProfesionalId())
                .status(turnos.getStatus())
                .dateTime(turnos.getDateTime())
                .paymentMethod(turnos.getPaymentMethod())
                .paymentStatus(turnos.getPaymentStatus())
                // .obraSocialId(turnos.getObraSocial() != null ? turnos.getObraSocial().getId() : null)
                .amount(turnos.getAmount())
                .results(turnos.getResults())
                .build();
    }

    public Turnos toEntity(TurnosDto dto) {
        if (dto == null) {
            return null;
        }

        Turnos turnos = new Turnos();
        turnos.setId(dto.getId());
        turnos.setName(dto.getName());
        turnos.setPacienteId(dto.getPacienteId());
        turnos.setProfesionalId(dto.getProfesionalId());
        turnos.setStatus(dto.getStatus());
        turnos.setDateTime(dto.getDateTime());
        turnos.setPaymentMethod(dto.getPaymentMethod());
        turnos.setPaymentStatus(dto.getPaymentStatus());
        // acá faltaria agregar obra social 
        turnos.setAmount(dto.getAmount());
        turnos.setResults(dto.getResults());
        return turnos;
    }

    public List<TurnosDto> toDtoList(List<Turnos> turnosList) {
        return turnosList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Turnos> toEntityList(List<TurnosDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}