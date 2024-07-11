package com.microservice.profesionales.service;

import com.microservice.profesionales.entities.Profesionales;

import java.util.List;

public interface IProfesionalesService {

    // Buscar todos los profesionales
    List<Profesionales> findAll();
    //Buscar profesionales por su id
    Profesionales findById(Long id);
    void save(Profesionales profesionales);
    List<Profesionales> findByIdTurno(Long idTurno);
}
