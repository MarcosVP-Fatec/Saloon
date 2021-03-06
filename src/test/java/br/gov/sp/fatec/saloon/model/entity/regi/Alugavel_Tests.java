package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.repository.regi.AlugavelRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.AlugavelTipoRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.ProprietarioService;

@SpringBootTest
@Transactional
@Rollback
public class Alugavel_Tests {

    @Autowired
    private AlugavelRepository      alugavelRepo;

    @Autowired
    private AlugavelTipoRepository  alugavelTipoRepo;

    @Autowired
    private ProprietarioService     proprietarioServiceRepo;

    final String DESCR_1        = "#TESTE_ALUGÁVEL_1";
    final String DESCR_2        = "#TESTE_ALUGÁVEL_2";
    final String ENDERECO_1     = "#ENDEREÇO_1_ALUGAVEL";
    final String APELIDO_PROP_1 = "#APELIDO_1_ALUGAVEL";
    final int    CAPACIDADE_1   = 100;
    final BigDecimal VALOR_1	= new BigDecimal(450.00);

    @Test
    void testeAlugavelIncluir() throws ParseException {
    	Alugavel alugavel = alugavelRepo.saveAndFlush(this.criaAlugavelTeste());
        assertTrue(alugavelRepo.existsByDescr(DESCR_1));
        assertEquals(DESCR_1, alugavel.getDescr());
        assertEquals(ENDERECO_1, alugavel.getEndereco());
        assertEquals(CAPACIDADE_1, alugavel.getCapacidade());
        assertEquals(VALOR_1, alugavel.getValor());
        assertEquals(alugavelTipoRepo.buscarPorId(1L).getDescr(), alugavel.getAlugavelTipo().getDescr());
        assertEquals("44444444444", alugavel.getProprietario().getCpf());
        
    }

    @Test
    void testeAlugavelAlterar() throws ParseException {
        
        Alugavel alugavel = alugavelRepo.save(this.criaAlugavelTeste());
        assertEquals(1, alugavelRepo.listaPorProprietarioApelido(APELIDO_PROP_1).size() );
        alugavel.setDescr(DESCR_2);
        alugavelRepo.save(alugavel);
        alugavelRepo.flush();
        assertTrue(alugavelRepo.existsByDescr(DESCR_2));
    }

    @Test
    void testeUsuarioDelete() throws ParseException {
        alugavelRepo.delete(alugavelRepo.save(this.criaAlugavelTeste()));
        assertFalse(alugavelRepo.existsByDescr(DESCR_1));
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private Alugavel criaAlugavelTeste() throws ParseException {

        Alugavel alugavel = new Alugavel();
        AlugavelTipo alugavelTipo = alugavelTipoRepo.buscarPorId(1L);
        Proprietario proprietario = proprietarioServiceRepo.inc( APELIDO_PROP_1
                                                               , "#teste1_proalugavel@saloon.br"
                                                               , "123"
                                                               , "#TESTE_NOME_PROP_ALUGAVEL"
                                                               , Data.toDate("12/04/1969")
                                                               , "44444444444");

        alugavel.setDescr(DESCR_1);
        alugavel.setProprietario(proprietario);
        alugavel.setAlugavelTipo(alugavelTipo);
        alugavel.setEndereco(ENDERECO_1);
        alugavel.setCapacidade(CAPACIDADE_1);
        alugavel.setValor(VALOR_1);
    
        return alugavel;
    }

}


    
