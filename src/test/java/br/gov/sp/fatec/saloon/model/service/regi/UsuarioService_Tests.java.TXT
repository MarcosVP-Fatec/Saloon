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

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
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

    public UsuarioService_Tests() throws ParseException {}

    final String APELIDO_1 = "#APELIDO_USUÁRIO_1";
    final String NOME_1 = "#NOME_USUÁRIO_1";
    final String NOME_2 = "#NOME_USUÁRIO_2";
    Date dtNascimento = Data.toDate("12/04/1969");


    @Test
    void testeUsuarioServiceIncluir()  {
        usuarioServiceRepo.inc(APELIDO_1, "#teste@teste.com.br", "ps123", NOME_1, dtNascimento, "99999999999", 3L);
        assertTrue(usuarioRepo.existsByApelido(APELIDO_1));
    }

  	@Test
	void testeDesenvolvitesteUsuarioServiceAlterar() {
        Usuario usu = usuarioServiceRepo.inc(APELIDO_1, "#teste@teste.com.br", "ps123", NOME_1, dtNascimento, "99999999999", 3L);
        usuarioServiceRepo.alt(usu.getId(), APELIDO_1, "#teste@teste.com.br", "ps123", NOME_2, dtNascimento, "99999999999", 3L);
        usuarioRepo.flush();
        assertEquals(NOME_2, usu.getNome());
    }    

    @Test
    void testeUsuarioServiceExcluir() throws ParseException {
        usuarioServiceRepo.del(usuarioServiceRepo.inc(APELIDO_1, "#teste@teste.com.br", "ps123", NOME_1, dtNascimento, "99999999999", 3L).getId());
        assertFalse(usuarioRepo.existsByApelido(APELIDO_1));
    }
}
