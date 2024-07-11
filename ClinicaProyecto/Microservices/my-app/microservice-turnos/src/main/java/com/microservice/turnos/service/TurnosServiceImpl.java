package com.microservice.turnos.service;

import com.microservice.turnos.client.PacientesClient;
import com.microservice.turnos.controller.dto.PacientesDto;
import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.http.response.PacientesByTurnosResponse;
import com.microservice.turnos.persistence.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnosServiceImpl implements ITurnosService{

    @Autowired
    private TurnosRepository turnosRepository;

    @Autowired
    private PacientesClient pacientesClient;
    @Override
    public List<Turnos> findAll() {
        return (List<Turnos>) turnosRepository.findAll();
    }

    @Override
    public Turnos findById(Long id) {
        return turnosRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Turnos turnos) {
        turnosRepository.save(turnos);
    }

    @Override
    public List<Turnos> findByIdPaciente(Long idPaciente) {
        return turnosRepository.findAllByPacienteId(idPaciente);
    }

    @Override
    public List<Turnos> findByIdProfesional(Long idProfesional) {
        return turnosRepository.findAllByProfesionalId(idProfesional);
    }

    @Override
    public PacientesByTurnosResponse findPacientesByIdTurnos(Long idTurno) {

        // Consulto el turno, acá si no lo encuentra crea un turno sin nada
        // es solo para prueba
        Turnos turnos = turnosRepository.findById(idTurno).orElse(new Turnos());

        // Obtener los pacientes
        List<PacientesDto> pacientesDtoList = pacientesClient.findAllPacientesByTurno(idTurno);
        return PacientesByTurnosResponse.builder()
                .name(turnos.getName())
                .pacientesDtoList(pacientesDtoList)
                .build();
    }

}
