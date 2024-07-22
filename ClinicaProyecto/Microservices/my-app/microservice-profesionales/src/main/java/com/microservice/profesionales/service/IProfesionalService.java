package com.microservice.profesionales.service;

import com.microservice.profesionales.controller.requests.ModifyProfesionalRequest;
import com.microservice.profesionales.controller.requests.ModifyStateRequest;
import com.microservice.profesionales.entities.Profesional;

import java.util.List;
import java.util.Optional;

public interface IProfesionalService {

    // Buscar todos los profesionales
    List<Profesional> findAll();

    // Buscar profesionales por su id
    Optional<Profesional> findById(Long id);

    // Guardar entidades en bd
    Profesional save(Profesional profesional);

    // Eliminar profesional por id
    void deleteById(Long id);

    // Actualizar entidad completa de un profesional por su id
    void update(Long id, ModifyProfesionalRequest modifyProfesionalRequest);

    // Actualizar (activar/desactivar) el estado referido a entreturnos y turnos online
    void updateState(Long id, ModifyStateRequest modifyStateRequest);
}
