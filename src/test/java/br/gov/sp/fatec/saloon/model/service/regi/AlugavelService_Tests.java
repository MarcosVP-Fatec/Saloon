package br.gov.sp.fatec.saloon.model.service.regi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.repository.regi.AlugavelRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
@Transactional
@Rollback
public class AlugavelService_Tests {

    @Autowired
    private AlugavelRepository      alugavelRepo;

    @Autowired
    private AlugavelService         alugavelServiceRepo;

    @Autowired
    private ProprietarioService     proprietarioServiceRepo;

    final String     DESCR_1        = "#TESTE_ALUGÁVEL_1";
    final String     DESCR_2        = "#TESTE_ALUGÁVEL_2";
    final String     ENDERECO_1     = "#ENDEREÇO_1_ALUGAVEL";
    final String     APELIDO_PROP_1 = "#APELIDO_1_ALUGAVEL";
    final int        CAPACIDADE_1   = 100;
    final BigDecimal VALOR_1        = new BigDecimal(550.00);

    @Test
    void testeAlugavelServiceIncluir() throws ParseException {
        this.criaAlugavelTeste();
        assertTrue(alugavelRepo.existsByDescr(DESCR_1));
    }
    
    @Test
    void testeAlugavelServiceAlterar() throws ParseException {
        Alugavel alugavel = this.criaAlugavelTeste();
        assertTrue(alugavelRepo.existsByDescr(DESCR_1));
        alugavel.setDescr(DESCR_2);
        alugavelRepo.save(alugavel);
        alugavelRepo.flush();
        assertFalse(alugavelRepo.existsByDescr(DESCR_1));
        assertTrue(alugavelRepo.existsByDescr(DESCR_2));
    }


    /*
     * Método que cria um Alugável para testes
     */
    public Alugavel criaAlugavelTeste() throws ParseException {
        
        Proprietario proprietario = proprietarioServiceRepo.persist( APELIDO_PROP_1
                                                                   , "#teste1_1_proalugavel@saloon.br"
                                                                   , "123"
                                                                   , "#TESTE_NOME_PROP_ALUGAVEL"
                                                                   , Data.toDate("12/04/1969")
                                                                   , "44444444444");

        return alugavelServiceRepo.persist( DESCR_1
                                          , proprietario.getId()
                                          , 1L
                                          , ENDERECO_1
                                          , CAPACIDADE_1
                                          , VALOR_1);
    }
}    
/*

  	@Test
	void testeProprietarioExcluir() throws ParseException {
        proprietarioRepo.delete(proprietarioRepo.save(this.criaProprietarioTeste()));
        assertFalse(proprietarioRepo.existsByApelido(APELIDO_1));
    }
*/


