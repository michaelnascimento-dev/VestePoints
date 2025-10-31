package com.vestepoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vestepoints.model.Loja;
import com.vestepoints.repository.LojaRepository;
import java.util.List;

@RestController
@RequestMapping("/lojas")
@CrossOrigin(origins = "*")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @GetMapping
    public List<Loja> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Loja buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}
