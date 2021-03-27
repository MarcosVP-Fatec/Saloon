package br.gov.sp.fatec.saloon.model.service.regi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;
import br.gov.sp.fatec.saloon.model.repository.regi.ParceiroRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.ParceiroService;

@SpringBootTest
@Transactional
@Rollback
public class ParceiroService_Tests {
    
    @Autowired
    private ParceiroRepository      parceiroRepo;

    @Autowired
    private ParceiroService         parceiroServiceRepo;

    public ParceiroService_Tests() throws ParseException {}

    final String    APELIDO_1   = "#TESTE_PARCEIRO_1";
    final String    NOME_1      = "#TESTE_1_NOME_PARCEIRO";
    final String    NOME_2      = "#TESTE_2_NOME_PARCEIRO";
    final Date      DTNASC_1    = Data.toDate("12/04/1969");
    final Date      DTFINAL     = Data.today();
    final String    EMAIL_1     = "#teste_1_parceiro@saloon.br";
    final String    CPF_1       = "66666666666";
    final String    SENHA_1     = "#SENHA_1_PARCEIRO";

    @Test
    void testeParceiroServiceIncluir() {
        this.criaParceiro();
        assertTrue(parceiroRepo.existsByApelido(APELIDO_1));
    }

  	@Test
	void testeParceiroServiceAlterar() {
        Parceiro parceiro = this.criaParceiro();
        parceiro.setNome(NOME_2);
        parceiroRepo.flush();
        assertTrue(parceiroRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
        assertEquals(NOME_2, parceiro.getNome());
    }    

  	@Test
	void testeParceiroServiceExcluir() {
        parceiroServiceRepo.delete(this.criaParceiro().getId());
        parceiroRepo.flush();
        assertFalse(parceiroRepo.existsByApelido(APELIDO_1));
    }    

    private Parceiro criaParceiro(){
        return parceiroServiceRepo.persist( APELIDO_1
                                          , EMAIL_1
                                          , SENHA_1
                                          , NOME_1
                                          , DTNASC_1
                                          , CPF_1);
    }
    
}

