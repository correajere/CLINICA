package com.microservice.profesionales.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.profesionales.entities.Profesional;
import lombok.Data;

@Data
public class CreateProfesionalRequest {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String lastName;

    @JsonProperty(required = true)
    private String phoneNumber;

    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    private String license;

    private String specialty;

    private String specialtyLicense;

    @JsonProperty(required = true)
    private String signature;

    @JsonProperty(required = true)
    private Boolean stateBetweenEncounter;

    @JsonProperty(required = true)
    private Boolean stateOnlineEncounter;

    public Profesional toEntity(){
        return Profesional.builder()
                .id(null)
                .name(name)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .email(email)
                .license(license)
                .specialty(specialty)
                .specialtyLicense(specialtyLicense)
                .signature(signature)
                .stateBetweenEncounter(stateBetweenEncounter)
                .stateOnlineEncounter(stateOnlineEncounter)
                .build();
    }
}
