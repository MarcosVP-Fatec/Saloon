package br.gov.sp.fatec.saloon.model.service.regi;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.regi.UsuarioRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
@Transactional
@Rollback
public class UsuarioService_Tests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioService usuarioServiceRepo;

    final String APELIDO_1 = "#APELIDO_USUÁRIO_1";
    final String NOME_1 = "#NOME_USUÁRIO_1";

    @Test
	void testeUsuarioServiceIncluir() {
        
        Date dtNascimento = Data.toDate("12/04/1969");

        usuarioServiceRepo.inc(APELIDO_1, "#teste@teste.com.br", "ps123", NOME_1, dtNascimento, "99999999999", 3);

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
