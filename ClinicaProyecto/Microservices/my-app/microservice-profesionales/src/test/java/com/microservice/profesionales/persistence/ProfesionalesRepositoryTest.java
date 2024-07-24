package com.microservice.profesionales.persistence;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.microservice.profesionales.entities.Profesional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProfesionalesRepositoryTest {
    @Mock
    private ProfesionalRepository profesionalRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAllByTurnoId() {
        Long idTurno = 1L;

        Profesional nutricionista = Profesional.builder()
        .name("Julian")
        .lastName("Alvarez")
        .phoneNumber("123456789")
        .email("julian.alvarez@mail.com")
        .license("ABC123")
        .specialty("Nutricionista")
        .specialtyLicense("NUTR456")
        .signature("Firma Julian Alvarez")
        .stateBetweenEncounter(true)
        .stateOnlineEncounter(true)
        .build();

        Profesional neurologo = Profesional.builder()
        .name("Emiliano")
        .lastName("Martinez")
        .phoneNumber("987654321")
        .email("doble.huevo@oa.com")
        .license("XYZ789")
        .specialty("Neurologia")
        .specialtyLicense("NEUR123")
        .signature("Firma Emiliano Martinez")
        .stateBetweenEncounter(false)
        .stateOnlineEncounter(false)
        .build();

        List<Profesional> profesionales = Arrays.asList(nutricionista, neurologo);

        when(profesionalRepository.findAllByTurnoId(idTurno)).thenReturn(profesionales);

        List<Profesional> resultados = profesionalRepository.findAllByTurnoId(idTurno);

        assertEquals(2, resultados.size());
        assertEquals(nutricionista, resultados.get(0));
        assertEquals(neurologo, resultados.get(1));
    
    }
}
