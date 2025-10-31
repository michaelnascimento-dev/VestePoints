package com.vestepoints.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.vestepoints.model.Loja;
import com.vestepoints.repository.LojaRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LojaRepository lojaRepo;

    public DataInitializer(LojaRepository lojaRepo) {
        this.lojaRepo = lojaRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (lojaRepo.count() == 0) { // verifica se não há lojas cadastradas
            Loja loja = new Loja();
            loja.setNome("Loja Associada");
            loja.setCnpj("12345678000199");   // pode usar um CNPJ fake válido
            loja.setCep("01001000");           // CEP de exemplo
            loja.setEstado("SP");              // estado de exemplo
            loja.setCidade("São Paulo");       // cidade de exemplo
            loja.setBairro("Centro");          // bairro de exemplo
            loja.setLogradouro("Rua Teste");   // logradouro de exemplo
            loja.setNumero("100");             // número de exemplo

            lojaRepo.save(loja);
            System.out.println("Loja teste 'Loja Associada' criada automaticamente!");
        }
    }
}
