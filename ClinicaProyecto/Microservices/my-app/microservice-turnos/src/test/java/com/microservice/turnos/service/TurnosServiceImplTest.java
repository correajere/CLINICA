package com.microservice.turnos.service;

import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.persistence.TurnosRepository;
import com.microservice.turnos.client.PacientesClient;
import com.microservice.turnos.controller.dto.PacientesDto;
import com.microservice.turnos.http.response.PacientesByTurnosResponse;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentStatus;
import com.microservice.turnos.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TurnosServiceImplTest {

    @Mock
    private TurnosRepository turnosRepository;

    @Mock
    private PacientesClient pacientesClient;

    @InjectMocks
    private TurnosServiceImpl turnosService;

    private Turnos turno;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        turno = Turnos.builder()
                .id(1L)
                .name("Consulta General")
                .pacienteId(1L)
                .profesionalId(1L)
                .status(TurnoStatus.PENDIENTE)
                .dateTime(LocalDateTime.now())
                .paymentMethod(PaymentMethod.PARTICULAR)
                .paymentStatus(PaymentStatus.IMPAGO)
                .amount(new BigDecimal("100.00"))
                .results("Pending")
                .build();
    }

    @DisplayName("Test para encontrar todos los turnos")
    @Test
    void findAll() {
        Turnos turno2 = Turnos.builder()
                .id(2L)
                .name("Consulta Especialista")
                .pacienteId(2L)
                .profesionalId(2L)
                .status(TurnoStatus.REALIZADO)
                .dateTime(LocalDateTime.now().plusDays(1))
                .paymentMethod(PaymentMethod.OBRA_SOCIAL)
                .paymentStatus(PaymentStatus.PAGADO)
                .amount(new BigDecimal("150.00"))
                .results("Completed")
                .build();

        given(turnosRepository.findAll()).willReturn(List.of(turno, turno2));

        List<Turnos> turnosActuales = turnosService.findAll();

        assertThat(turnosActuales).isNotNull();
        assertThat(turnosActuales.size()).isEqualTo(2);
        assertThat(turnosActuales).containsExactlyInAnyOrder(turno, turno2);
    }

    @DisplayName("Test para eliminar un turno")
    @Test
    void delete() {
        Long turnoId = 1L;

        turnosService.deleteById(turnoId);

        verify(turnosRepository, times(1)).deleteById(turnoId);
    }

    @DisplayName("Test para encontrar un turno por ID")
    @Test
    void findById() {
        given(turnosRepository.findById(1L)).willReturn(Optional.of(turno));

        Turnos turnoEncontrado = turnosService.findById(1L);

        assertThat(turnoEncontrado).isNotNull();
        assertThat(turnoEncontrado.getId()).isEqualTo(1L);
        assertThat(turnoEncontrado.getName()).isEqualTo("Consulta General");
    }

    @DisplayName("Test para guardar un turno")
    @Test
    void save() {
        given(turnosRepository.save(any(Turnos.class))).willReturn(turno);

        Turnos turnoGuardado = turnosService.save(turno);

        assertThat(turnoGuardado).isNotNull();
        verify(turnosRepository, times(1)).save(turno);
    }

    @DisplayName("Test para encontrar turnos por ID de paciente")
    @Test
    void findByPacienteId() {
        Long idPaciente = 1L;
        given(turnosRepository.findAllByPacienteId(idPaciente)).willReturn(List.of(turno));

        List<Turnos> turnosActuales = turnosService.findByPacienteId(idPaciente);

        assertThat(turnosActuales).isNotNull();
        assertThat(turnosActuales.size()).isEqualTo(1);
        assertThat(turnosActuales).contains(turno);
    }

    @DisplayName("Test para encontrar turnos por ID de profesional")
    @Test
    void findByProfesionalId() {
        Long idProfesional = 1L;
        given(turnosRepository.findAllByProfesionalId(idProfesional)).willReturn(List.of(turno));

        List<Turnos> turnosActuales = turnosService.findByProfesionalId(idProfesional);

        assertThat(turnosActuales).isNotNull();
        assertThat(turnosActuales.size()).isEqualTo(1);
        assertThat(turnosActuales).contains(turno);
    }

    @DisplayName("Test para encontrar pacientes por ID de turno")
    @Test
    void findPacientesByIdTurnos() {
        Long idTurno = 1L;
        List<PacientesDto> pacientesDtoList = Arrays.asList(
                new PacientesDto("John", "Doe", "john@example.com", idTurno)
        );

        given(turnosRepository.findById(idTurno)).willReturn(Optional.of(turno));
        given(pacientesClient.findAllPacientesByTurno(idTurno)).willReturn(pacientesDtoList);

        PacientesByTurnosResponse response = turnosService.findPacientesByIdTurnos(idTurno);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(turno.getName());
        assertThat(response.getPacientesDtoList()).isEqualTo(pacientesDtoList);
    }

    @DisplayName("Test para actualizar el estado de un turno")
    @Test
    void updateStatus() {
        Long turnoId = 1L;
        TurnoStatus newStatus = TurnoStatus.REALIZADO;

        given(turnosRepository.findById(turnoId)).willReturn(Optional.of(turno));
        given(turnosRepository.save(any(Turnos.class))).willReturn(turno);

        Turnos updatedTurno = turnosService.updateStatus(turnoId, newStatus);

        assertThat(updatedTurno).isNotNull();
        assertThat(updatedTurno.getStatus()).isEqualTo(newStatus);
        verify(turnosRepository, times(1)).save(turno);
    }

    @DisplayName("Test para actualizar el estado de pago de un turno")
    @Test
    void updatePaymentStatus() {
        Long turnoId = 1L;
        PaymentStatus newPaymentStatus = PaymentStatus.PAGADO;

        given(turnosRepository.findById(turnoId)).willReturn(Optional.of(turno));
        given(turnosRepository.save(any(Turnos.class))).willReturn(turno);

        Turnos updatedTurno = turnosService.updatePaymentStatus(turnoId, newPaymentStatus);

        assertThat(updatedTurno).isNotNull();
        assertThat(updatedTurno.getPaymentStatus()).isEqualTo(newPaymentStatus);
        verify(turnosRepository, times(1)).save(turno);
    }
}