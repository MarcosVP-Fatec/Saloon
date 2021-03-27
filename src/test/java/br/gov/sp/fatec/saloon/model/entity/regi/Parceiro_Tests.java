package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.regi.ParceiroRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.ParceiroService;

@SpringBootTest
@Transactional
@Rollback
public class Parceiro_Tests {
    
    @Autowired
    private ParceiroRepository      parceiroRepo;

    @Autowired
    private ParceiroService         parceiroServiceRepo;

    public Parceiro_Tests() throws ParseException {}

    final String    APELIDO_1   = "#TESTE_PARCEIRO_1";
    final String    NOME_1      = "#TESTE_1_NOME_PARCEIRO";
    final String    NOME_2      = "#TESTE_2_NOME_PARCEIRO";
    final Date      DTNASC_1    = Data.toDate("12/04/1969");
    final String    EMAIL_1     = "#teste_1_parceiro@saloon.br";
    final String    CPF_1       = "66666666666";
    final String    SENHA_1     = "#SENHA_1_PARCEIRO";

    @Test
    void testeParceiroIncluir(){
        parceiroRepo.save(this.criaParceiro());
        assertTrue(parceiroRepo.existsByApelido(APELIDO_1));
    }

    @Test
    void testeParceiroAlterar(){
        Parceiro parc = parceiroRepo.save(this.criaParceiro());
        assertTrue(parceiroRepo.existsByApelido(APELIDO_1));
        assertFalse(parceiroRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
        parc.setNome(NOME_2);
        parceiroRepo.save(parc);
        parceiroRepo.flush();
        assertTrue(parceiroRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
    }

  	@Test
	void testeParceiroExcluir(){
        parceiroRepo.delete(parceiroRepo.save(this.criaParceiro()));
        assertFalse(parceiroRepo.existsByApelido(APELIDO_1));
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private Parceiro criaParceiro() {
        return parceiroServiceRepo.persist( APELIDO_1
                                          , EMAIL_1
                                          , SENHA_1
                                          , NOME_1
                                          , DTNASC_1
                                          , CPF_1);
    }
}
