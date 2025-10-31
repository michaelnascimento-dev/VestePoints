package com.vestepoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vestepoints.model.Credito;
import java.util.Optional;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
    Optional<Credito> findByDoadorIdDoador(Long idDoador);
}
