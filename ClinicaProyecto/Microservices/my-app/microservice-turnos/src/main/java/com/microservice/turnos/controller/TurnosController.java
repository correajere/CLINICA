package com.microservice.turnos.controller;

import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentStatus;
import com.microservice.turnos.service.ITurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turnos")
public class TurnosController {

    @Autowired
    private ITurnosService turnosService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTurno(@RequestBody Turnos turno) {
        turnosService.save(turno);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateTurno(@PathVariable Long id, @RequestBody Turnos turno) {
        Optional<Turnos> turnoActualizado = turnosService.update(id, turno);
        return ResponseEntity.ok(turnoActualizado);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllTurnos() {
        return ResponseEntity.ok(turnosService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(turnosService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTurno(@PathVariable Long id) {
        turnosService.deleteById(id);
    }

    @GetMapping("/search-by-paciente/{pacienteId}")
    public ResponseEntity<?> findByPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(turnosService.findByPacienteId(pacienteId));
    }

    @GetMapping("/search-by-profesional/{profesionalId}")
    public ResponseEntity<?> findByProfesionalId(@PathVariable Long profesionalId) {
        return ResponseEntity.ok(turnosService.findByProfesionalId(profesionalId));
    }

    @GetMapping("/search-by-status/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable TurnoStatus status) {
        return ResponseEntity.ok(turnosService.findByStatus(status));
    }

    @GetMapping("/search-by-date-range")
    public ResponseEntity<?> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(turnosService.findByDateTimeBetween(start, end));
    }

    @GetMapping("/search-by-payment-status/{paymentStatus}")
    public ResponseEntity<?> findByPaymentStatus(@PathVariable PaymentStatus paymentStatus) {
        return ResponseEntity.ok(turnosService.findByPaymentStatus(paymentStatus));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam TurnoStatus status) {
        Turnos updatedTurno = turnosService.updateStatus(id, status);
        return ResponseEntity.ok(updatedTurno);
    }

    @PatchMapping("/{id}/payment-status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam PaymentStatus paymentStatus) {
        Turnos updatedTurno = turnosService.updatePaymentStatus(id, paymentStatus);
        return ResponseEntity.ok(updatedTurno);
    }
}