package com.microservice.turnos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.microservice.turnos.enums.TurnoStatus;
import com.microservice.turnos.enums.PaymentMethod;
import com.microservice.turnos.enums.PaymentStatus;

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

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TurnoStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "obra_social_id")
    // private ObraSocial obraSocial;

    private BigDecimal amount;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String results;
}
