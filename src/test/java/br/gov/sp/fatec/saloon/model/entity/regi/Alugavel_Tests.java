package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.repository.regi.AlugavelRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.AlugavelTipoRepository;
import br.gov.sp.fatec.saloon.model.service.regi.AlugavelService;
import br.gov.sp.fatec.saloon.model.service.regi.ProprietarioService;
import br.gov.sp.fatec.saloon.model.tool.Data;

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

    Alugavel_Tests() throws ParseException {}

    final String DESCR_1 = "#TESTE_ALUGÁVEL_1";
    final String DESCR_2 = "#TESTE_ALUGÁVEL_2";
    final String ENDERECO_1 = "#ENDEREÇO_1_ALUGAVEL";
    final String APELIDO_PROP_1 = "#ENDEREÇO_1_ALUGAVEL";
    final int CAPACIDADE_1 = 100;

    @Test
    void testeAlugavelIncluir() throws ParseException {
        alugavelRepo.save(this.criaAlugavelTeste());
        assertTrue(alugavelRepo.existsByDescr(DESCR_1));
    }

    @Test
    void testeAlugavelAlterar() {
        
        Alugavel alugavel = alugavelRepo.save(this.criaAlugavelTeste());
        assertTrue(alugavelRepo.findByProprietarioApelido(APELIDO_PROP_1).size() > 0);
/*
        usu.setNome(NOME_2);
        usuarioRepo.save(usu);
        usuarioRepo.flush();
        assertTrue(usuarioRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
        */
    }

    // @Test
    // void testeUsuarioDelete() {
    // usuarioRepo.delete(usuarioRepo.save(this.criaUsuario()));
    // assertFalse(usuarioRepo.existsByApelido(APELIDO_1));
    // }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private Alugavel criaAlugavelTeste() {

        Alugavel alugavel = new Alugavel();
        AlugavelTipo alugavelTipo = alugavelTipoRepo.buscarPorId(1L);
        Proprietario proprietario = proprietarioServiceRepo.persist(null, "#PROP_ALUGAVEL_1", "#teste1_proalugavel@saloon.br", "123", "#TESTE_NOME_PROP_ALUGAVEL", Data.toDate("12/04/1969"), "44444444444");

        alugavel.setDescr(DESCR_1);
        alugavel.setProprietario(proprietario);
        alugavel.setAlugavelTipo(alugavelTipo);
        alugavel.setEndereco(ENDERECO_1);
        alugavel.setCapacidade(CAPACIDADE_1);
    
        return alugavel;
    }

}


    
