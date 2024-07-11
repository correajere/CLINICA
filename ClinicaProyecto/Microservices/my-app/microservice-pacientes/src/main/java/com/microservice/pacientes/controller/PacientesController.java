package com.microservice.pacientes.controller;

import com.microservice.pacientes.entities.Pacientes;
import com.microservice.pacientes.service.IPacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {

    @Autowired
    private IPacientesService pacientesService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePacientes(@RequestBody Pacientes pacientes){
        pacientesService.save(pacientes);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllPacientes(){
        return ResponseEntity.ok(pacientesService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(pacientesService.findById(id));
    }

    //Hago un endpoint para que desde turnos pueda aplicar la lógica de llamarlo y conectarlos
    @GetMapping("/search-by-turno/{idTurno}")
    public ResponseEntity<?> findByIdTurno(@PathVariable Long idTurno){
        return ResponseEntity.ok(pacientesService.findByIdTurno(idTurno));
    }
}
