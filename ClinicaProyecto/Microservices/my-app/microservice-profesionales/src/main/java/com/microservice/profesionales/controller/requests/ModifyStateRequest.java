package com.microservice.profesionales.controller.requests;

import lombok.Data;

@Data
public class ModifyStateRequest {
    private Boolean stateBetweenEncounter;
    private Boolean stateOnlineEncounter;
}
