package br.gov.sp.fatec.saloon.model.service.stat;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@SpringBootTest
@Transactional
@Rollback
public class UsuarioNivelService_Tests {

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

    @Autowired
    private UsuarioNivelService usuarioNivelServiceRepo;
    
  	@Test
	void testeUsuarioNivelServiceIncluir() {
        
        usuarioNivelServiceRepo.inc(999999L
                                   ,"#TESTE_NIVEL"
                                   , true
                                   , false
                                  , true
                                  , false);

        assertTrue(usuarioNivelRepo.existsById(999999L));
    }

  	@Test
	void testeDesenvolvimentoServiceAlterar() {
        
        usuarioNivelServiceRepo.inc(999999L
                                   ,"#TESTE_NIVEL"
                                   , true
                                   , false
                                   , true
                                   , false);

        usuarioNivelServiceRepo.alt(999999L
                                   ,"#ALT_TESTE_NIVEL"
                                   , true
                                   , false
                                   , true
                                   , false);

        assertTrue(usuarioNivelRepo.existsByDescr("#ALT_TESTE_NIVEL"));
    }    

}

