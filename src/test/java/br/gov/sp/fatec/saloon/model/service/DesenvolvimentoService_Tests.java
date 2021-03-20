package br.gov.sp.fatec.saloon.model.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.UsuarioNivelRepository;

@SpringBootTest
@Transactional
@Rollback
public class DesenvolvimentoService_Tests {

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

    @Autowired
    private DesenvolvimentoService desenvRepo;
    
  	@Test
	void testeDesenvolvimentoServiceIncluir() {
        
        desenvRepo.incUsuarioNivel(999999L
                                  ,"#TESTE_NIVEL"
                                  , true
                                  , false
                                  , true
                                  , false);

        assertTrue(usuarioNivelRepo.existsById(999999L));
    }

  	@Test
	void testeDesenvolvimentoServiceAlterar() {
        
        desenvRepo.incUsuarioNivel(999999L
                                  ,"#TESTE_NIVEL"
                                  , true
                                  , false
                                  , true
                                  , false);

        desenvRepo.altUsuarioNivel(999999L
                                  ,"#ALT_TESTE_NIVEL"
                                  , true
                                  , false
                                  , true
                                  , false);

        assertTrue(usuarioNivelRepo.existsByDescr("#ALT_TESTE_NIVEL"));
    }    

}