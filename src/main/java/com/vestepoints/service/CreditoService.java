package com.vestepoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vestepoints.model.Credito;
import com.vestepoints.model.Doador;
import com.vestepoints.repository.CreditoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository repository;

    public Optional<Credito> buscarPorDoador(Long idDoador) {
        return repository.findByDoadorIdDoador(idDoador);
    }

    public void creditar(Doador doador, BigDecimal valor) {
        Optional<Credito> opt = repository.findByDoadorIdDoador(doador.getIdDoador());
        if (opt.isPresent()) {
            Credito c = opt.get();
            BigDecimal novoSaldo = c.getSaldoAtual() != null
                    ? c.getSaldoAtual().add(valor)
                    : valor;
            c.setSaldoAtual(novoSaldo);
            c.setDataUltimaAtt(LocalDateTime.now());
            repository.save(c);
        } else {
            Credito novo = new Credito();
            novo.setDoador(doador);
            novo.setSaldoAtual(valor);
            novo.setDataUltimaAtt(LocalDateTime.now());
            repository.save(novo);
        }
    }
}