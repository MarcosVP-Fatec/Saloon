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
import br.gov.sp.fatec.saloon.service.regi.UsuarioService;

@SpringBootTest
@Transactional
@Rollback
public class UsuarioService_Tests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioService usuarioServiceRepo;

    public UsuarioService_Tests() throws ParseException {}

    final String APELIDO_1  = "#APELIDO_USUÁRIO_1";
    final String NOME_1     = "#NOME_USUÁRIO_1";
    final String NOME_2     = "#NOME_USUÁRIO_2";
    final String EMAIL_1    = "#teste1_usuario@saloon.br";
    final String SENHA_1    = "#SENHA_1";
    final Date   DTNASC_1   = Data.toDate("12/04/1969");
    final String CPF_1      = "99999999999";
    final Long   ID_NIVEL_1 = 3L;

    @Test
    void testeUsuarioServiceIncluir()  {
        this.criaUsuario();
        assertTrue(usuarioRepo.existsByApelido(APELIDO_1));
    }

  	@Test
	void testeDesenvolvitesteUsuarioServiceAlterar() {
        Usuario usu = this.criaUsuario();
        usu.setNome(NOME_2);
        usuarioRepo.save(usu);
        usuarioRepo.flush();
        assertEquals(NOME_2, usu.getNome());
    }    

    @Test
    void testeUsuarioServiceExcluir() {
        usuarioServiceRepo.delete(this.criaUsuario().getId());
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
