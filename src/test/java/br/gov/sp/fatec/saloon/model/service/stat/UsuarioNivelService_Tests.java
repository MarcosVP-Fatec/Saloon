package br.gov.sp.fatec.saloon.model.service.stat;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;
import br.gov.sp.fatec.saloon.service.stat.UsuarioNivelService;

@SpringBootTest
@Transactional
@Rollback
public class UsuarioNivelService_Tests {

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

    @Autowired
    private UsuarioNivelService usuarioNivelServiceRepo;

    final String KEY_NIVEL1  = "ROLE_ADMIN";
    final Long   ID_NIVEL    = 9999999L;
    final String DESCR_1     = "#TESTE_NIVEL_1";
    final String DESCR_2     = "#TESTE_NIVEL_2";
    
  	@Test
	void testeUsuarioNivelServiceIncluir() {
        
        usuarioNivelServiceRepo.persist( ID_NIVEL
                                       , KEY_NIVEL1
                                       , DESCR_1
                                       , true
                                       , false
                                       , true
                                       , false);

        assertTrue(usuarioNivelRepo.existsById(ID_NIVEL));
    }

    @Test
	void testeUsuarioNivelServiceAlterar() {
        
        usuarioNivelServiceRepo.persist( ID_NIVEL
                                       , KEY_NIVEL1
                                       , DESCR_1
                                       , true
                                       , false
                                       , true
                                       , false);

        usuarioNivelServiceRepo.persist( ID_NIVEL
                                       , KEY_NIVEL1
                                       , DESCR_2
                                       , true
                                       , false
                                       , true
                                       , false);

        assertTrue(usuarioNivelRepo.existsByDescr(DESCR_2));

    }

}

