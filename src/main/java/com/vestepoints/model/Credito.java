package com.vestepoints.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredito;

    @OneToOne
    @JoinColumn(name = "id_doador", nullable = false, unique = true)
    private Doador doador;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoAtual;

    @Column(nullable = false)
    private LocalDateTime dataUltimaAtt;
}
