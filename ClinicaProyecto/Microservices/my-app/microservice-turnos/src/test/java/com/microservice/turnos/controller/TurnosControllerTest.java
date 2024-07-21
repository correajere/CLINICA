package com.microservice.turnos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.service.ITurnosService;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentStatus;
import com.microservice.turnos.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TurnosController.class)
class TurnosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITurnosService turnosService;

    @Autowired
    private ObjectMapper objectMapper;

    private Turnos turno1;
    private Turnos turno2;

    @BeforeEach
    void setUp() {
        turno1 = Turnos.builder()
                .id(1L)
                .name("Consulta 1")
                .pacienteId(1L)
                .profesionalId(1L)
                .dateTime(LocalDateTime.now())
                .status(TurnoStatus.PENDIENTE)
                .paymentMethod(PaymentMethod.OBRA_SOCIAL)
                .paymentStatus(PaymentStatus.IMPAGO)
                .amount(new BigDecimal("100.00"))
                .results("Pending results")
                .build();

        turno2 = Turnos.builder()
                .id(2L)
                .name("Consulta 2")
                .pacienteId(2L)
                .profesionalId(2L)
                .dateTime(LocalDateTime.now().plusDays(1))
                .status(TurnoStatus.REALIZADO)
                .paymentMethod(PaymentMethod.PARTICULAR)
                .paymentStatus(PaymentStatus.PAGADO)
                .amount(new BigDecimal("150.00"))
                .results("Completed results")
                .build();
    }

    @DisplayName("Test para crear un turno")
    @Test
    void testSaveTurno() throws Exception {
        mockMvc.perform(post("/api/turnos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(turno1)))
                .andExpect(status().isCreated());
    }

    @DisplayName("Test para actualizar un turno")
    @Test
    void testUpdateTurno() throws Exception {
        given(turnosService.update(1L, turno1)).willReturn(Optional.of(turno1));

        mockMvc.perform(post("/api/turnos/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(turno1)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Optional.of(turno1))));
    }

    @DisplayName("Test para obtener todos los turnos")
    @Test
    void testFindAllTurnos() throws Exception {
        given(turnosService.findAll()).willReturn(Arrays.asList(turno1, turno2));

        mockMvc.perform(get("/api/turnos/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(turno1, turno2))));
    }

    @DisplayName("Test para obtener un turno por ID")
    @Test
    void testFindById() throws Exception {
        given(turnosService.findById(1L)).willReturn(turno1);

        mockMvc.perform(get("/api/turnos/search/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(turno1)));
    }

    @DisplayName("Test para eliminar un turno")
    @Test
    void testDeleteTurno() throws Exception {
        mockMvc.perform(delete("/api/turnos/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(turnosService, times(1)).deleteById(1L);
    }

    @DisplayName("Test para obtener turnos por ID de paciente")
    @Test
    void testFindByPacienteId() throws Exception {
        given(turnosService.findByPacienteId(1L)).willReturn(Arrays.asList(turno1));

        mockMvc.perform(get("/api/turnos/search-by-paciente/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(turno1))));
    }

    @DisplayName("Test para actualizar el estado de un turno")
    @Test
    void testUpdateStatus() throws Exception {
        given(turnosService.updateStatus(1L, TurnoStatus.REALIZADO)).willReturn(turno1);

        mockMvc.perform(patch("/api/turnos/1/status")
                        .param("status", "REALIZADO")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(turno1)));
    }

    @DisplayName("Test para actualizar el estado de pago de un turno")
    @Test
    void testUpdatePaymentStatus() throws Exception {
        given(turnosService.updatePaymentStatus(1L, PaymentStatus.PAGADO)).willReturn(turno1);

        mockMvc.perform(patch("/api/turnos/1/payment-status")
                        .param("paymentStatus", "PAGADO")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(turno1)));
    }
}