package com.vestepoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vestepoints.model.Doador;
import com.vestepoints.service.DoadorService;

import java.util.List;

@RestController
@RequestMapping("/doadores")
@CrossOrigin(origins = "*")
public class DoadorController {

    @Autowired
    private DoadorService service;

    @PostMapping
    public Doador cadastrar(@RequestBody Doador doador) {
        return service.cadastrar(doador);
    }

    @PostMapping("/login")
    public Doador login(@RequestBody Doador dadosLogin) {
        return service.login(dadosLogin.getEmail(), dadosLogin.getSenha());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doador> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Doador> buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // NOVO ENDPOINT: Listar todos os doadores
    @GetMapping
    public ResponseEntity<List<Doador>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
