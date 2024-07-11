package com.microservice.pacientes.service;

import com.microservice.pacientes.entities.Pacientes;

import java.util.List;

public interface IPacientesService {
    // Buscar todos los pacientes
    List<Pacientes> findAll();
    //Buscar pacientes por su id
    Pacientes findById(Long id);
    void save(Pacientes pacientes);
    List<Pacientes> findByIdTurno(Long idTurno);

}
