package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

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
public class Usuario_Tests {
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    final String APELIDO_1 = "#TESTE_USU";

  	@Test
	void testeUsuarioIncluir() {
        usuarioRepo.save(this.criaUsuario());
        assertTrue(usuarioRepo.existsByApelido(APELIDO_1));
    }

  	@Test
	void testeUsuarioDelete() {
        usuarioRepo.delete(usuarioRepo.save(this.criaUsuario()));
        assertFalse(usuarioRepo.existsByApelido(APELIDO_1));
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private Usuario criaUsuario(){
        
        Usuario usuario = new Usuario();
        Date dtNascimento = Data.today();

        usuario.setApelido("#TESTE_USU");
        usuario.setEmail("#teste@teste.com.br");
        usuario.setSenha("pw123");
        usuario.setNome("#TESTE_NOME");
        usuario.setDtNascimento(dtNascimento);
        usuario.setCpf("99999999999");
        usuario.setUsuarioNivel(2L);

        return usuario;
    }

}
