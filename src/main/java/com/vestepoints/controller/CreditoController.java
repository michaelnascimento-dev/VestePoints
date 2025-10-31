package com.vestepoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vestepoints.model.Credito;
import com.vestepoints.service.CreditoService;

@RestController
@RequestMapping("/creditos")
@CrossOrigin(origins = "*")
public class CreditoController {

    @Autowired
    private CreditoService service;

    @GetMapping("/doador/{id}")
    public Credito buscarPorDoador(@PathVariable Long id) {
        return service.buscarPorDoador(id).orElse(null);
    }
}
