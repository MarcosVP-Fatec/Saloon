package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.regi.UsuarioRepository;
import br.gov.sp.fatec.saloon.model.service.regi.UsuarioService;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
@Transactional
@Rollback
public class Usuario_Tests {
    
    @Autowired
    private UsuarioRepository   usuarioRepo;

    @Autowired
    private UsuarioService      usuarioServiceRepo;

    public Usuario_Tests() throws ParseException {}

    final String APELIDO_1  = "#APELIDO_USUÁRIO_1";
    final String NOME_1     = "#NOME_USUÁRIO_1";
    final String NOME_2     = "#NOME_USUÁRIO_2";
    final String EMAIL_1    = "#teste1_usuario@saloon.br";
    final String SENHA_1    = "#SENHA_1";
    final Date   DTNASC_1   = Data.toDate("12/04/1969");
    final String CPF_1      = "99999999999";
    final Long   ID_NIVEL_1 = 2L;

    @Test
	void testeUsuarioIncluir() {
        usuarioRepo.save(this.criaUsuario());
        assertTrue(usuarioRepo.existsByApelido(APELIDO_1));
    }

	void testeUsuarioAlterar() {
        Usuario usu = usuarioRepo.save(this.criaUsuario());
        assertFalse(usuarioRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
        usu.setNome(NOME_2);
        usuarioRepo.save(usu);
        usuarioRepo.flush();
        assertTrue(usuarioRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
    }

    @Test
	void testeUsuarioDelete() {
        usuarioRepo.delete(usuarioRepo.save(this.criaUsuario()));
        assertFalse(usuarioRepo.existsByApelido(APELIDO_1));
    }

    /*
     * Função padrão de criação de usuário
     */
    private Usuario criaUsuario(){
        return usuarioServiceRepo.persist( APELIDO_1
                                         , EMAIL_1
                                         , SENHA_1
                                         , NOME_1
                                         , DTNASC_1
                                         , CPF_1
                                         , ID_NIVEL_1);
    }
}
