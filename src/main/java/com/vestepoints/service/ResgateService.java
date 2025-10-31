package com.vestepoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vestepoints.model.*;
import com.vestepoints.repository.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResgateService {

    @Autowired
    private ResgateRepository resgateRepo;

    @Autowired
    private CreditoRepository creditoRepo;

    public Resgate criar(Resgate resgate) {
        Credito credito = creditoRepo.findByDoadorIdDoador(resgate.getDoador().getIdDoador())
                .orElseThrow(() -> new RuntimeException("Crédito não encontrado para este doador"));

        if (credito.getSaldoAtual() == null) {
            throw new RuntimeException("Saldo atual está nulo para este doador");
        }

        BigDecimal novoSaldo = credito.getSaldoAtual().subtract(resgate.getDesconto());
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo insuficiente para resgate");
        }

        credito.setSaldoAtual(novoSaldo);
        credito.setDataUltimaAtt(LocalDateTime.now());
        creditoRepo.save(credito);

        resgate.setDataResgate(LocalDateTime.now());
        return resgateRepo.save(resgate);
    }


    public List<Resgate> listarPorDoador(Long idDoador) {
        return resgateRepo.findByDoadorIdDoador(idDoador);
    }
}
