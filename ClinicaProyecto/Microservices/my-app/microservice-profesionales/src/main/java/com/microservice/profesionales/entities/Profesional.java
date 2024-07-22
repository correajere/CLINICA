package com.microservice.profesionales.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "profesionales")
@AllArgsConstructor
@NoArgsConstructor
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last-name")
    private String lastName;

    @Column(name = "phone-number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "license")
    private String license;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "specialty-license")
    private String specialtyLicense;

    @Column(name = "signature")
    private String signature;

    // Activar entreturnos
    @Column(name = "state-between-encounter")
    private Boolean stateBetweenEncounter;

    // Activar turno online
    @Column(name = "state-online-encounter")
    private Boolean stateOnlineEncounter;
}
