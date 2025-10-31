package com.vestepoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vestepoints.model.Doador;
import com.vestepoints.repository.DoadorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository repository;

    public Doador cadastrar(Doador doador) {
        doador.setDataCadastro(LocalDateTime.now());
        return repository.save(doador);
    }

    public Doador login(String email, String senha) {
        Optional<Doador> opt = repository.findByEmail(email);
        if (opt.isPresent() && opt.get().getSenha().equals(senha)) {
            return opt.get();
        }
        return null;
    }

    public Optional<Doador> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Doador> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    // ✅ Novo método para listar todos os doadores
    public List<Doador> listarTodos() {
        return repository.findAll();
    }
}
