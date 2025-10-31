package com.vestepoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vestepoints.model.Resgate;
import com.vestepoints.service.ResgateService;
import java.util.List;

@RestController
@RequestMapping("/resgates")
@CrossOrigin(origins = "*")
public class ResgateController {

    @Autowired
    private ResgateService service;

    @PostMapping
    public Resgate criar(@RequestBody Resgate resgate) {
        return service.criar(resgate);
    }

    @GetMapping("/doador/{id}")
    public List<Resgate> listarPorDoador(@PathVariable Long id) {
        return service.listarPorDoador(id);
    }
}
