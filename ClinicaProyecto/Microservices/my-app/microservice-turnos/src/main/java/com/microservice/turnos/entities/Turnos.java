package com.microservice.turnos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "turnos")
@AllArgsConstructor
@NoArgsConstructor
public class Turnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "profesional_id")
    private Long profesionalId;
}
