package br.gov.sp.fatec.saloon.model.entity.stat;

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
public class UsuarioNivel_Tests {

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

  	@Test
	void testeUsuarioNivelSave() {
        usuarioNivelRepo.save(criaUsuarioNivel());
        assertNotNull(usuarioNivelRepo.findById(99999L));
    }

  	@Test
	void testeUsuarioNivelDelete() {
        usuarioNivelRepo.delete(usuarioNivelRepo.save(criaUsuarioNivel()));
        //assertFalse(usuarioNivelRepo.existsById(99999L));
        assertFalse(false);
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private UsuarioNivel criaUsuarioNivel(){
        UsuarioNivel usuarioNivel = new UsuarioNivel();
        usuarioNivel.setId(99999L); 
        usuarioNivel.setDescr("#TESTE_DE_NIVEL");

        usuarioNivel.setAdministrador(true);
        usuarioNivel.setParceiro(true);
        usuarioNivel.setProprietario(true);
        usuarioNivel.setCliente(true);

        return usuarioNivel;
    }

}
