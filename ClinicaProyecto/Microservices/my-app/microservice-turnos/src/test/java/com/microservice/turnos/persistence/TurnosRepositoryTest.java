package com.microservice.turnos.persistence;

import com.microservice.turnos.entities.Turnos;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentMethod;
import com.microservice.turnos.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TurnosRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TurnosRepository turnosRepository;

    private Turnos turno1;
    private Turnos turno2;

    @BeforeEach
    void setUp() {
        turno1 = Turnos.builder()
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

        turno2 = Turnos.builder()
                .name("Consulta Especialista")
                .pacienteId(2L)
                .profesionalId(1L)
                .status(TurnoStatus.REALIZADO)
                .dateTime(LocalDateTime.now().plusDays(1))
                .paymentMethod(PaymentMethod.OBRA_SOCIAL)
                .paymentStatus(PaymentStatus.PAGADO)
                .amount(new BigDecimal("150.00"))
                .results("Completed")
                .build();

        entityManager.persist(turno1);
        entityManager.persist(turno2);
        entityManager.flush();
    }

    @Test
    @DisplayName("Deberia devolver todos los turnos")
    void shouldFindAllTurnos() {
        List<Turnos> turnos = (List<Turnos>) turnosRepository.findAll();
        
        assertThat(turnos).hasSize(2);
    }

    @Test
    @DisplayName("Deberia encontrar un turno por id")
    void shouldFindTurnoById() {
        Turnos foundTurno = turnosRepository.findById(turno1.getId()).orElse(null);
        
        assertThat(foundTurno).isNotNull();
        assertThat(foundTurno.getName()).isEqualTo(turno1.getName());
    }

    @Test
    @DisplayName("Deberia encontrar los turnos por id de paciente")
    void shouldFindTurnosByPacienteId() {
        List<Turnos> turnos = turnosRepository.findAllByPacienteId(1L);
        
        assertThat(turnos).hasSize(1);
        assertThat(turnos.get(0).getName()).isEqualTo(turno1.getName());
    }

    @Test
    @DisplayName("Deberia encontrar los turnos por id de profesional")
    void shouldFindTurnosByProfesionalId() {
        List<Turnos> turnos = turnosRepository.findAllByProfesionalId(1L);
        
        assertThat(turnos).hasSize(2);
    }

    @Test
    @DisplayName("Deberia encontrar los pacientes por id de turno")
    void shouldFindTurnosByStatus() {
        List<Turnos> turnos = turnosRepository.findByStatus(TurnoStatus.PENDIENTE);
        
        assertThat(turnos).hasSize(1);
        assertThat(turnos.get(0).getName()).isEqualTo(turno1.getName());
    }

    @Test
    @DisplayName("Deberia encontrar los turnos por fecha y hora entre")
    void shouldFindTurnosByDateTimeBetween() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(2);
        
        List<Turnos> turnos = turnosRepository.findByDateTimeBetween(start, end);
        
        assertThat(turnos).hasSize(2);
    }

    @Test
    @DisplayName("Deberia encontrar los turnos por estado de pago")
    void shouldFindTurnosByPaymentStatus() {
        List<Turnos> turnos = turnosRepository.findByPaymentStatus(PaymentStatus.IMPAGO);
        
        assertThat(turnos).hasSize(1);
        assertThat(turnos.get(0).getName()).isEqualTo(turno1.getName());
    }

    @Test
    @DisplayName("Deberia guardar un turno")
    void shouldSaveTurno() {
        Turnos newTurno = Turnos.builder()
                .name("New Consulta")
                .pacienteId(3L)
                .profesionalId(2L)
                .status(TurnoStatus.PENDIENTE)
                .dateTime(LocalDateTime.now().plusDays(2))
                .paymentMethod(PaymentMethod.AMBOS)
                .paymentStatus(PaymentStatus.PARCIAL)
                .amount(new BigDecimal("200.00"))
                .results("New")
                .build();

        Turnos savedTurno = turnosRepository.save(newTurno);
        
        assertThat(savedTurno).isNotNull();
        assertThat(savedTurno.getId()).isNotNull();
        assertThat(savedTurno.getName()).isEqualTo(newTurno.getName());
    }
}