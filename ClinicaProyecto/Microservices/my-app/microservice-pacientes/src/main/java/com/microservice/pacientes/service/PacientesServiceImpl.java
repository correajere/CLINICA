package com.microservice.pacientes.service;

import com.microservice.pacientes.entities.Pacientes;
import com.microservice.pacientes.persistence.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacientesServiceImpl implements IPacientesService{

    @Autowired
    private PacientesRepository pacientesRepository;
    @Override
    public List<Pacientes> findAll() {
        return (List<Pacientes>) pacientesRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        pacientesRepository.deleteById(id);
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

    @Override
    public Optional<Pacientes> updatePaciente(Long id, Pacientes paciente) {
        return pacientesRepository.findById(id).map(existingPaciente -> {
            if (paciente.getName() != null) {
                existingPaciente.setName(paciente.getName());
            }
            if (paciente.getLastName() != null) {
                existingPaciente.setLastName(paciente.getLastName());
            }
            if (paciente.getEmail() != null) {
                existingPaciente.setEmail(paciente.getEmail());
            }
            if (paciente.getTurnoId() != null) {
                existingPaciente.setTurnoId(paciente.getTurnoId());
            }
            // Actualiza otros campos según sea necesario
            return pacientesRepository.save(existingPaciente);
        });
    }
}
