package com.microservice.pacientes.service;

import com.microservice.pacientes.entities.Pacientes;
import com.microservice.pacientes.persistence.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientesServiceImpl implements IPacientesService{

    @Autowired
    private PacientesRepository pacientesRepository;
    @Override
    public List<Pacientes> findAll() {
        return (List<Pacientes>) pacientesRepository.findAll();
    }

    @Override
    public Pacientes findById(Long id) {
        return pacientesRepository.findById(id).orElseThrow();
        // orElseThrow trata de buscarlo y si no que tire error
    }

    @Override
    public void save(Pacientes pacientes) {
        pacientesRepository.save(pacientes);
    }

    @Override
    public List<Pacientes> findByIdTurno(Long idTurno) {
        return pacientesRepository.findAllByTurnoId(idTurno);
    }
}
