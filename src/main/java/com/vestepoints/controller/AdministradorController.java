package com.vestepoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vestepoints.model.Administrador;
import com.vestepoints.service.AdministradorService;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @PostMapping
    public Administrador cadastrar(@RequestBody Administrador admin) {
        return service.cadastrar(admin);
    }

    @PostMapping("/login")
    public Administrador login(@RequestBody Administrador dadosLogin) {
        return service.login(dadosLogin.getEmail(), dadosLogin.getSenha());
    }
}
