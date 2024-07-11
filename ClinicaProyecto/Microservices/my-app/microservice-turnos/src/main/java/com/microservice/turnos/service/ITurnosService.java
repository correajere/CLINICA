package com.microservice.turnos.service;

import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.http.response.PacientesByTurnosResponse;

import java.util.List;
public interface ITurnosService {

    // Buscar todos los profesionales
    List<Turnos> findAll();
    //Buscar profesionales por su id
    Turnos findById(Long id);
    void save(Turnos turnos);
    List<Turnos> findByIdPaciente(Long idPaciente);
    List<Turnos> findByIdProfesional(Long idProfesional);

    PacientesByTurnosResponse findPacientesByIdTurnos(Long idTurno);
}
