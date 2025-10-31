package com.vestepoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vestepoints.model.Doacao;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    List<Doacao> findByDoadorIdDoador(Long idDoador);
    List<Doacao> findByLojaIdLoja(Long idLoja);
}
