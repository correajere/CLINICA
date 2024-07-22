package com.microservice.profesionales.controller.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.profesionales.entities.Profesional;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfesionalResponse {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String license;
    private String specialty;
    private String specialtyLicense;
    private String signature;
    private Boolean stateBetweenEncounter;
    private Boolean stateOnlineEncounter;

    public static ProfesionalResponse from(Profesional profesionales) {
        return ProfesionalResponse.builder()
                .id(profesionales.getId())
                .name(profesionales.getName())
                .lastName(profesionales.getLastName())
                .phoneNumber(profesionales.getPhoneNumber())
                .email(profesionales.getEmail())
                .license(profesionales.getLicense())
                .specialty(profesionales.getSpecialty())
                .specialtyLicense(profesionales.getSpecialtyLicense())
                .signature(profesionales.getSignature())
                .stateBetweenEncounter(profesionales.getStateBetweenEncounter())
                .stateOnlineEncounter(profesionales.getStateOnlineEncounter())
                .build();
    }
}
