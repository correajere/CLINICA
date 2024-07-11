package com.microservice.turnos.controller;

import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.service.ITurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turnos")
public class TurnosController {

    @Autowired
    private ITurnosService turnosService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTurnos(@RequestBody Turnos turnos){
        turnosService.save(turnos);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllTurnos(){
        return ResponseEntity.ok(turnosService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(turnosService.findById(id));
    }

    @GetMapping("/search-pacientes/{idTurno}")
    public ResponseEntity<?> findPacientesByIdTurnos(@PathVariable Long idTurno){
        return ResponseEntity.ok(turnosService.findPacientesByIdTurnos(idTurno));
    }
}
