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

@SpringBootTest
@Transactional
@Rollback
public class ParceiroService_Tests {
    
    @Autowired
    private ParceiroRepository      parceiroRepo;

    @Autowired
    private ParceiroService         parceiroServiceRepo;

    public ParceiroService_Tests() throws ParseException {}

    final String APELIDO_1 = "#APELIDO_USUÁRIO_PARC_1";
    final String NOME_1    = "#NOME_USUÁRIO_PARC_1";
    final String NOME_2    = "#NOME_USUÁRIO_PARC_2";
    final String EMAIL_1   = "#teste_1_parceiro@saloon.br";
    final Date   DTNASC_1  = Data.toDate("12/04/1969");
    final Date   DTFINAL   = Data.today();

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
        parceiroServiceRepo.del(parceiroServiceRepo.inc(APELIDO_1, EMAIL_1, "pw345", NOME_1, DTNASC_1, "66666666666").getId());
        parceiroRepo.flush();
        assertFalse(parceiroRepo.existsByApelido(APELIDO_1));
    }    

    private Parceiro criaParceiro(){
        return parceiroServiceRepo.inc(APELIDO_1, EMAIL_1, "pw345", NOME_1, DTNASC_1, "66666666666");
    }
    
}

