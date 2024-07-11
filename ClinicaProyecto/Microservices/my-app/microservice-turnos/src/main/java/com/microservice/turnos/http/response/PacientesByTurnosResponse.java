package com.microservice.turnos.http.response;

import com.microservice.turnos.controller.dto.PacientesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacientesByTurnosResponse {
    private String name;
    private List<PacientesDto> pacientesDtoList;
}
