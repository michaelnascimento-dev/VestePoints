package com.vestepoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vestepoints.model.Doador;
import java.util.Optional;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
    Optional<Doador> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Doador> findByCpf(String cpf);
}