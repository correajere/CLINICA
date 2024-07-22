package com.microservice.profesionales.controller;

import com.microservice.profesionales.controller.requests.CreateProfesionalRequest;
import com.microservice.profesionales.controller.requests.ModifyProfesionalRequest;
import com.microservice.profesionales.controller.requests.ModifyStateRequest;
import com.microservice.profesionales.controller.responses.ProfesionalResponse;
import com.microservice.profesionales.entities.Profesional;
import com.microservice.profesionales.service.IProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    @Autowired
    private IProfesionalService profesionalService;

    @PostMapping("/create")
    public ResponseEntity<Profesional> createNewProfesional(@RequestBody CreateProfesionalRequest newProfesional){
        try {
            Profesional created = profesionalService.save(newProfesional.toEntity());
            URI ubi = URI.create(String.format("/profesionales/%d", created.getId()));

            return ResponseEntity.created(ubi).body(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfesionalResponse>> findAllProfesionales(){
        try {
            var profesionales = profesionalService.findAll().stream()
                    .map(ProfesionalResponse::from)
                    .toList();

            return profesionales.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(profesionales);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ProfesionalResponse> findById(@PathVariable Long id){
        try {
            Optional<Profesional> profesional = profesionalService.findById(id);

            return profesional.map(ProfesionalResponse::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        try {
            profesionalService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/updateEntity/{id}")
    public ResponseEntity<Object> updateEntity(@PathVariable Long id,
                                               @RequestBody ModifyProfesionalRequest modifyProfesionalRequest) {
        try {
            profesionalService.update(id, modifyProfesionalRequest);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/updateState/{id}")
    public ResponseEntity<Object> updateState(@PathVariable Long id,
                                              @RequestBody ModifyStateRequest modifyStateRequest) {
        try {
            profesionalService.updateState(id, modifyStateRequest);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
