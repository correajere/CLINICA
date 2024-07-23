package com.microservice.turnos.service;

import com.microservice.turnos.client.PacientesClient;
import com.microservice.turnos.controller.dto.PacientesDto;
import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.http.response.PacientesByTurnosResponse;
import com.microservice.turnos.persistence.TurnosRepository;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnosServiceImpl implements ITurnosService {

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
        return turnosRepository.findById(id).orElseThrow(() -> new RuntimeException("Turno not found"));
    }

    @Override
    public Turnos save(Turnos turnos) {
        return turnosRepository.save(turnos);
    }


    @Override
    public Optional<Turnos> update(Long id, Turnos turnos) {
        return turnosRepository.findById(id).map(existingTurno -> {
            if (turnos.getName() != null) {
                existingTurno.setName(turnos.getName());
            }
            if (turnos.getPacienteId() != null) {
                existingTurno.setPacienteId(turnos.getPacienteId());
            }
            if (turnos.getProfesionalId() != null) {
                existingTurno.setProfesionalId(turnos.getProfesionalId());
            }
            if (turnos.getStatus() != null) {
                existingTurno.setStatus(turnos.getStatus());
            }
            if (turnos.getDateTime() != null) {
                existingTurno.setDateTime(turnos.getDateTime());
            }
            if (turnos.getPaymentMethod() != null) {
                existingTurno.setPaymentMethod(turnos.getPaymentMethod());
            }
            if (turnos.getPaymentStatus() != null) {
                existingTurno.setPaymentStatus(turnos.getPaymentStatus());
            }
            if (turnos.getAmount() != null) {
                existingTurno.setAmount(turnos.getAmount());
            }
            if (turnos.getResults() != null) {
                existingTurno.setResults(turnos.getResults());
            }
            return turnosRepository.save(existingTurno);
        });
    }

    @Override
    public void deleteById(Long id) {
        turnosRepository.deleteById(id);
    }

    @Override
    public List<Turnos> findByPacienteId(Long idPaciente) {
        return turnosRepository.findAllByPacienteId(idPaciente);
    }

    @Override
    public List<Turnos> findByProfesionalId(Long idProfesional) {
        return turnosRepository.findAllByProfesionalId(idProfesional);
    }

    @Override
    public PacientesByTurnosResponse findPacientesByIdTurnos(Long idTurno) {
        Turnos turnos = findById(idTurno);
        List<PacientesDto> pacientesDtoList = pacientesClient.findAllPacientesByTurno(idTurno);
        return PacientesByTurnosResponse.builder()
                .name(turnos.getName())
                .pacientesDtoList(pacientesDtoList)
                .build();
    }

    @Override
    public List<Turnos> findByStatus(TurnoStatus status) {
        return turnosRepository.findByStatus(status);
    }

    @Override
    public List<Turnos> findByDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        return turnosRepository.findByDateTimeBetween(start, end);
    }

    @Override
    public List<Turnos> findByPaymentStatus(PaymentStatus paymentStatus) {
        return turnosRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public Turnos updateStatus(Long id, TurnoStatus status) {
        Turnos turno = findById(id);
        turno.setStatus(status);
        return turnosRepository.save(turno);
    }

    @Override
    public Turnos updatePaymentStatus(Long id, PaymentStatus paymentStatus) {
        Turnos turno = findById(id);
        turno.setPaymentStatus(paymentStatus);
        return turnosRepository.save(turno);
    }
}