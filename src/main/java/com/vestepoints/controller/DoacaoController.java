package com.vestepoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vestepoints.model.Doacao;
import com.vestepoints.repository.DoacaoRepository;
import com.vestepoints.service.DoacaoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doacoes")
@CrossOrigin(origins = "*")
public class DoacaoController {

    @Autowired
    private DoacaoService service;

    @Autowired
    private DoacaoRepository doacaoRepo;

    @PostMapping
    public Doacao criar(@RequestBody Doacao doacao) {
        return service.criar(doacao);
    }

    @GetMapping
    public List<Doacao> listarTodas() {
        return doacaoRepo.findAll();
    }

    @GetMapping("/doador/{id}")
    public List<Doacao> listarPorDoador(@PathVariable Long id) {
        return service.listarPorDoador(id);
    }

    @GetMapping("/loja/{id}")
    public List<Doacao> listarPorLoja(@PathVariable Long id) {
        return service.listarPorLoja(id);
    }

    @PutMapping("/{idDoacao}/validar/{idAdmin}")
    public ResponseEntity<?> validar(@PathVariable Long idDoacao, @PathVariable Long idAdmin) {
        try {
            Doacao validada = service.validarDoacao(idDoacao, idAdmin);
            return ResponseEntity.ok(validada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<?> aprovar(@PathVariable Long id) {
        try {
            Doacao aprovada = service.aprovarDoacao(id);
            return ResponseEntity.ok(aprovada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<?> recusar(@PathVariable Long id) {
        try {
            Doacao recusada = service.recusarDoacao(id);
            return ResponseEntity.ok(recusada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        Optional<Doacao> doacao = doacaoRepo.findById(id);
        if (doacao.isPresent()) {
            if (doacao.get().getAdminValidador() == null) {
                doacaoRepo.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Doação já validada e não pode ser cancelada.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
    }
}