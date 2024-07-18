package com.microservice.pacientes.service;

import com.microservice.pacientes.entities.Pacientes;

import java.util.List;
import java.util.Optional;

public interface IPacientesService {
    // Buscar todos los pacientes
    List<Pacientes> findAll();
    //Buscar pacientes por su id

    void delete(Long id);
    Pacientes findById(Long id);
    void save(Pacientes pacientes);
    List<Pacientes> findByIdTurno(Long idTurno);
    Optional<Pacientes> updatePaciente(Long id, Pacientes paciente);


}
