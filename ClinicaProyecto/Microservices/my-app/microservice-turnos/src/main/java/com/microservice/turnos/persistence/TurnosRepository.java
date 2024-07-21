package com.microservice.turnos.persistence;

import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnosRepository extends CrudRepository<Turnos, Long> {

    List<Turnos> findAllByProfesionalId(Long idProfesional);

    List<Turnos> findAllByPacienteId(Long idPaciente);

    List<Turnos> findByStatus(TurnoStatus status);

    List<Turnos> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Turnos> findByPaymentStatus(PaymentStatus paymentStatus);
}