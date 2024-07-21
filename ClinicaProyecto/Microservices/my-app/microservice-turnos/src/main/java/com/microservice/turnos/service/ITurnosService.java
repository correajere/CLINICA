package com.microservice.turnos.service;

import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.http.response.PacientesByTurnosResponse;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ITurnosService {

    List<Turnos> findAll();
    Turnos findById(Long id);
    Turnos save(Turnos turnos);
    Optional<Turnos> update(Long id, Turnos turnos);
    void deleteById(Long id);
    List<Turnos> findByPacienteId(Long idPaciente);
    List<Turnos> findByProfesionalId(Long idProfesional);
    PacientesByTurnosResponse findPacientesByIdTurnos(Long idTurno);
    

    List<Turnos> findByStatus(TurnoStatus status);
    List<Turnos> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Turnos> findByPaymentStatus(PaymentStatus paymentStatus);
    Turnos updateStatus(Long id, TurnoStatus status);
    Turnos updatePaymentStatus(Long id, PaymentStatus paymentStatus);
}