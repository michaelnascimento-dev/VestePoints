package com.vestepoints.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "doacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoacao;

    @ManyToOne
    @JoinColumn(name = "id_doador", nullable = false)
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "id_loja", nullable = false)
    private Loja loja;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Administrador adminValidador;

    @Column(nullable = false)
    private Integer qtdePecas;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal creditosGerados;

    @Column(nullable = false)
    private LocalDateTime dataDoacao;

    @Column
    private Boolean aprovada;
}
