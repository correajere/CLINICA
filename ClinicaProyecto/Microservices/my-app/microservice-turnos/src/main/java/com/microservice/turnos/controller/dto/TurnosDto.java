package com.microservice.turnos.controller.dto;

import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentMethod;
import com.microservice.turnos.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TurnosDto {

    private Long id;
    private String name;
    private Long pacienteId;
    private Long profesionalId;
    private TurnoStatus status;
    private LocalDateTime dateTime;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Long obraSocialId;
    private BigDecimal amount;
    private String results;
}