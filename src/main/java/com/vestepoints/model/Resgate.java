package com.vestepoints.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "resgate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resgate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResgate;

    @ManyToOne
    @JoinColumn(name = "id_doador", nullable = false)
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "id_loja", nullable = false)
    private Loja loja;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal desconto;

    @Column(nullable = false)
    private LocalDateTime dataResgate;
}
