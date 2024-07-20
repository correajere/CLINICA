package com.microservice.pacientes.persistence;

import com.microservice.pacientes.entities.Pacientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)

public class PacienteRepositoryTests {

    @Autowired
    private PacientesRepository pacientesRepository;

    private Pacientes paciente;

    @BeforeEach
    void setUp() {
        paciente = Pacientes.builder()
                .name("Leo")
                .lastName("Fraghe")
                .email("leo@gmail.com")
                .turnoId(3L)
                .build();

        pacientesRepository.save(paciente);
    }

    @DisplayName("Test para encontrar todos los pacientes")
    @Test
    void findAll() {
        // Given
        Pacientes paciente2 = Pacientes.builder()
                .name("Pedro")
                .lastName("Lopez")
                .email("plo@gmail.com")
                .turnoId(1L)
                .build();
        pacientesRepository.save(paciente2);

        // Act
        List<Pacientes> pacientes = (List<Pacientes>) pacientesRepository.findAll();

        // Then
        assertThat(pacientes).isNotNull();
        assertThat(pacientes.size()).isEqualTo(2);
        assertThat(pacientes).contains(paciente, paciente2);
    }

    @DisplayName("Test para eliminar un paciente")
    @Test
    void delete() {
        // Act
        pacientesRepository.deleteById(paciente.getId());

        // Then
        Optional<Pacientes> deletedPaciente = pacientesRepository.findById(paciente.getId());
        assertThat(deletedPaciente).isEmpty();
    }

    @DisplayName("Test para encontrar un paciente por ID")
    @Test
    void findById() {
        // Act
        Optional<Pacientes> foundPaciente = pacientesRepository.findById(paciente.getId());

        // Then
        assertThat(foundPaciente).isPresent();
        assertThat(foundPaciente.get()).isEqualTo(paciente);
    }

    @DisplayName("Test para guardar un paciente")
    @Test
    void save() {
        // Given
        Pacientes pacienteAGuardar = Pacientes.builder()
                .name("Pedro")
                .lastName("Lopez")
                .email("plo@gmail.com")
                .turnoId(1L)
                .build();

        // Act
        Pacientes pacienteGuardado = pacientesRepository.save(pacienteAGuardar);

        // Then
        assertThat(pacienteGuardado).isNotNull();
        assertThat(pacienteGuardado.getId()).isNotNull();
        assertThat(pacienteGuardado.getName()).isEqualTo("Pedro");
        assertThat(pacienteGuardado.getLastName()).isEqualTo("Lopez");
        assertThat(pacienteGuardado.getEmail()).isEqualTo("plo@gmail.com");
        assertThat(pacienteGuardado.getTurnoId()).isEqualTo(1L);
    }
    @DisplayName("Test para encontrar pacientes por ID de turno")
    @Test
    void findByIdTurno() {
        // Given
        Pacientes paciente2 = Pacientes.builder()
                .name("Pedro")
                .lastName("Lopez")
                .email("plo@gmail.com")
                .turnoId(3L)
                .build();
        pacientesRepository.save(paciente2);

        // Act
        List<Pacientes> pacientes = pacientesRepository.findAllByTurnoId(3L);

        // Then
        assertThat(pacientes).isNotNull();
        assertThat(pacientes.size()).isEqualTo(2);
        assertThat(pacientes).contains(paciente, paciente2);
    }

    @DisplayName("Test para actualizar un paciente")
    @Test
    void updatePaciente() {
        // Given
        paciente.setName("UpdatedName");
        paciente.setLastName("UpdatedLastName");
        paciente.setEmail("updated@gmail.com");
        paciente.setTurnoId(4L);

        // Act
        Pacientes updatedPaciente = pacientesRepository.save(paciente);

        // Then
        assertThat(updatedPaciente).isNotNull();
        assertThat(updatedPaciente.getName()).isEqualTo("UpdatedName");
        assertThat(updatedPaciente.getLastName()).isEqualTo("UpdatedLastName");
        assertThat(updatedPaciente.getEmail()).isEqualTo("updated@gmail.com");
        assertThat(updatedPaciente.getTurnoId()).isEqualTo(4L);
    }
}

