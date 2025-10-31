package com.vestepoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vestepoints.model.Resgate;
import java.util.List;

public interface ResgateRepository extends JpaRepository<Resgate, Long> {
    List<Resgate> findByDoadorIdDoador(Long idDoador);
}
