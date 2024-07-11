package com.microservice.turnos.client;

import com.microservice.turnos.controller.dto.PacientesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "mscv-paciente", url = "localhost:8080/api/pacientes")
public interface PacientesClient {

    @GetMapping("/search-by-turno/{idTurno}")
    List<PacientesDto> findAllPacientesByTurno(@PathVariable Long idTurno);
}
