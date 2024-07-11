package com.microservice.profesionales.controller;

import com.microservice.profesionales.entities.Profesionales;
import com.microservice.profesionales.service.IProfesionalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalesController {

    @Autowired
    private IProfesionalesService profesionalesService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProfesionales(@RequestBody Profesionales profesionales){
        profesionalesService.save(profesionales);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllProfesionales(){
        return ResponseEntity.ok(profesionalesService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(profesionalesService.findById(id));
    }
}
