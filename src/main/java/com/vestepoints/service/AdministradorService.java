package com.vestepoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vestepoints.model.Administrador;
import com.vestepoints.repository.AdministradorRepository;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public Administrador cadastrar(Administrador admin) {
        return repository.save(admin);
    }

    public Administrador login(String email, String senha) {
        Optional<Administrador> opt = repository.findByEmail(email);
        if (opt.isPresent() && opt.get().getSenha().equals(senha)) {
            return opt.get();
        }
        return null;
    }
}
