package com.microservice.profesionales.controller.requests;

import lombok.Data;

@Data
public class ModifyProfesionalRequest {

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
}
