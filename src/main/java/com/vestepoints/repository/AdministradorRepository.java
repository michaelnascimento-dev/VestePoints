package com.vestepoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vestepoints.model.Administrador;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByEmail(String email);
}
