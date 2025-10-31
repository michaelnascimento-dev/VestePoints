package com.vestepoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vestepoints.model.*;
import com.vestepoints.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepo;

    @Autowired
    private AdministradorRepository adminRepo;

    @Autowired
    private CreditoRepository creditoRepo;

    @Autowired
    private DoadorRepository doadorRepo;

    @Autowired
    private LojaRepository lojaRepo;

    public Doacao criar(Doacao dto) {
        if (dto.getDoador() == null || dto.getDoador().getIdDoador() == null) {
            throw new RuntimeException("Doador não informado");
        }

        if (dto.getLoja() == null || dto.getLoja().getIdLoja() == null) {
            throw new RuntimeException("Loja não informada");
        }

        Doador doador = doadorRepo.findById(dto.getDoador().getIdDoador())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        Loja loja = lojaRepo.findById(dto.getLoja().getIdLoja())
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        Doacao nova = new Doacao();
        nova.setDoador(doador);
        nova.setLoja(loja);
        nova.setQtdePecas(dto.getQtdePecas());
        nova.setDataDoacao(LocalDateTime.now());
        nova.setCreditosGerados(calcularCreditos(dto.getQtdePecas())); // ajuste conforme sua lógica
        nova.setAprovada(null);
        nova.setAdminValidador(null);

        return doacaoRepo.save(nova);
    }

    public List<Doacao> listarPorDoador(Long idDoador) {
        return doacaoRepo.findByDoadorIdDoador(idDoador);
    }

    public List<Doacao> listarPorLoja(Long idLoja) {
        return doacaoRepo.findByLojaIdLoja(idLoja);
    }

    public Doacao validarDoacao(Long idDoacao, Long idAdmin) {
        Doacao doacao = doacaoRepo.findById(idDoacao)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        Administrador admin = adminRepo.findById(idAdmin)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        doacao.setAdminValidador(admin);
        doacao.setAprovada(true);
        doacaoRepo.save(doacao);

        atualizarCredito(doacao);

        return doacao;
    }

    public Doacao aprovarDoacao(Long id) {
        Doacao doacao = doacaoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        doacao.setAprovada(true);
        doacaoRepo.save(doacao);

        atualizarCredito(doacao);

        return doacao;
    }

    public Doacao recusarDoacao(Long id) {
        Doacao doacao = doacaoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        doacao.setAprovada(false);
        return doacaoRepo.save(doacao);
    }

    private void atualizarCredito(Doacao doacao) {
        Credito credito = creditoRepo.findByDoadorIdDoador(doacao.getDoador().getIdDoador())
                .orElse(new Credito());

        credito.setDoador(doacao.getDoador());

        if (credito.getSaldoAtual() == null) {
            credito.setSaldoAtual(doacao.getCreditosGerados());
        } else {
            credito.setSaldoAtual(credito.getSaldoAtual().add(doacao.getCreditosGerados()));
        }

        credito.setDataUltimaAtt(LocalDateTime.now());
        creditoRepo.save(credito);
    }

    private java.math.BigDecimal calcularCreditos(int qtdePecas) {
        // Exemplo: 5 reais por peça
        return java.math.BigDecimal.valueOf(qtdePecas * 5);
    }
}