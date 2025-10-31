package com.vestepoints.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loja")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLoja;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 200)
    private String logradouro;

    @Column(nullable = false, length = 20)
    private String numero;
}
