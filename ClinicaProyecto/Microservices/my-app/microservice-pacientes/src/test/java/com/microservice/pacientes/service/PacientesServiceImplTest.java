package com.microservice.pacientes.service;

import com.microservice.pacientes.entities.Pacientes;
import com.microservice.pacientes.persistence.PacientesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PacientesServiceImplTest {

    @Mock // Creamos simulacro del repositorio
    private PacientesRepository pacientesRepository;

    @InjectMocks // Inyectar el servicio dentro del Mock
    private PacientesServiceImpl pacientesService;

    private Pacientes paciente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        paciente = Pacientes.builder()
                .id(1L) // Agrega el ID aquí
                .name("Leo")
                .lastName("Fraghe")
                .email("leo@gmail.com")
                .turnoId(3L)
                .build();
    }

    @DisplayName("Test para encontrar los pacientes")
    @Test
    void findAll() {
        // Given - Configuración

        Pacientes paciente1 = Pacientes.builder()
                .id(2L) // Agrega el ID aquí
                .name("Pedro")
                .lastName("Lopez")
                .email("plo@gmail.com")
                .turnoId(1L)
                .build();

        given(pacientesRepository.findAll()).willReturn(List.of(paciente1, paciente));

        // Act

        List<Pacientes> pacientesActuales = pacientesService.findAll();

        // Then

        assertThat(pacientesActuales).isNotNull();
        assertThat(pacientesActuales.size()).isEqualTo(2);
        assertThat(pacientesActuales).containsExactlyInAnyOrder(paciente, paciente1);
    }

    @DisplayName("Test para eliminar un paciente")
    @Test
    void delete() {
        Long pacienteId = 1L;

        pacientesService.delete(pacienteId);

        verify(pacientesRepository, times(1)).deleteById(pacienteId);
    }

    @DisplayName("Test para encontrar un paciente por ID")
    @Test
    void findById() {
        // Given

        given(pacientesRepository.findById(1L)).willReturn(Optional.of(paciente));

        // When

        Pacientes pacienteEncontrado = pacientesService.findById(1L);

        // Then

        assertThat(pacienteEncontrado).isNotNull();
        assertThat(pacienteEncontrado.getId()).isEqualTo(1L);
        assertThat(pacienteEncontrado.getName()).isEqualTo("Leo");
        assertThat(pacienteEncontrado.getLastName()).isEqualTo("Fraghe");
    }

    @DisplayName("Test para guardar un paciente")
    @Test
    void save() {
        // Given - Configuración
        Pacientes pacienteToSave = Pacientes.builder()
                .name("Pedro")
                .lastName("Lopez")
                .email("plo@gmail.com")
                .turnoId(1L)
                .build();

        // Act
        pacientesService.save(pacienteToSave);

        // Then
        verify(pacientesRepository, times(1)).save(pacienteToSave);
    }

    @DisplayName("Test para encontrar pacientes por ID de turno")
    @Test
    void findByIdTurno() {
        // Given
        Long idTurno = 1L;
        Pacientes paciente1 = Pacientes.builder()
                .id(2L)
                .name("Pedro")
                .lastName("Lopez")
                .email("plo@gmail.com")
                .turnoId(idTurno)
                .build();

        given(pacientesRepository.findAllByTurnoId(idTurno)).willReturn(List.of(paciente1, paciente));

        // Act
        List<Pacientes> pacientesActuales = pacientesService.findByIdTurno(idTurno);

        // Then
        assertThat(pacientesActuales).isNotNull();
        assertThat(pacientesActuales.size()).isEqualTo(2);
        assertThat(pacientesActuales).containsExactlyInAnyOrder(paciente, paciente1);
    }

    @DisplayName("Test para actualizar un paciente")
    @Test
    void updatePaciente() {
        // Given
        Pacientes updatedPaciente = Pacientes.builder()
                .name("UpdatedName")
                .lastName("UpdatedLastName")
                .email("updated@gmail.com")
                .turnoId(4L)
                .build();

        given(pacientesRepository.findById(1L)).willReturn(Optional.of(paciente));
        given(pacientesRepository.save(any(Pacientes.class))).willAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Pacientes> pacienteActualizado = pacientesService.updatePaciente(1L, updatedPaciente);

        // Then
        assertThat(pacienteActualizado).isPresent();
        assertThat(pacienteActualizado.get().getName()).isEqualTo("UpdatedName");
        assertThat(pacienteActualizado.get().getLastName()).isEqualTo("UpdatedLastName");
        assertThat(pacienteActualizado.get().getEmail()).isEqualTo("updated@gmail.com");
        assertThat(pacienteActualizado.get().getTurnoId()).isEqualTo(4L);
    }
}

