package com.microservice.pacientes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.pacientes.entities.Pacientes;
import com.microservice.pacientes.service.IPacientesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PacientesController.class)
class PacientesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPacientesService pacientesService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pacientes paciente1;
    private Pacientes paciente2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paciente1 = Pacientes.builder()
                .id(1L)
                .name("Pedro")
                .lastName("Lopez")
                .email("plo@gmail.com")
                .turnoId(1L)
                .build();

        paciente2 = Pacientes.builder()
                .id(2L)
                .name("Leo")
                .lastName("Fraghe")
                .email("leo@gmail.com")
                .turnoId(3L)
                .build();
    }

    @DisplayName("Test para crear un paciente")
    @Test
    void testSavePaciente() throws Exception {
        mockMvc.perform(post("/api/pacientes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paciente1)))
                .andExpect(status().isCreated());
    }

    @DisplayName("Test para actualizar un paciente")
    @Test
    void testUpdatePaciente() throws Exception {
        given(pacientesService.updatePaciente(1L, paciente1)).willReturn(Optional.of(paciente1));

        mockMvc.perform(post("/api/pacientes/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paciente1)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Optional.of(paciente1))));
    }

    @DisplayName("Test para obtener todos los pacientes")
    @Test
    void testFindAllPacientes() throws Exception {
        given(pacientesService.findAll()).willReturn(Arrays.asList(paciente1, paciente2));

        mockMvc.perform(get("/api/pacientes/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(paciente1, paciente2))));
    }

    @DisplayName("Test para obtener un paciente por ID")
    @Test
    void testFindById() throws Exception {
        given(pacientesService.findById(1L)).willReturn(paciente1);

        mockMvc.perform(get("/api/pacientes/search/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(paciente1)));
    }

    @DisplayName("Test para obtener pacientes por ID de turno")
    @Test
    void testFindByIdTurno() throws Exception {
        given(pacientesService.findByIdTurno(3L)).willReturn(Arrays.asList(paciente2));

        mockMvc.perform(get("/api/pacientes/search-by-turno/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(paciente2))));
    }

    @DisplayName("Test para eliminar un paciente")
    @Test
    void testDeletePaciente() throws Exception {
        mockMvc.perform(delete("/api/pacientes/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(pacientesService, times(1)).delete(1L);
    }
}
