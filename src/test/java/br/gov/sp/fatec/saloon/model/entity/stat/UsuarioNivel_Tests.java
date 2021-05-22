package br.gov.sp.fatec.saloon.model.entity.stat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class UsuarioNivel_Tests {

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;
    
    final String NIVEL_1	=	"ROLE_#TESTE_DE_NIVEL";
    final String DESCR_1	=	"#TESTE_DE_NIVEL";

    @Test
	void testeUsuarioNivelSave() {
    	UsuarioNivel usuarioNivel = usuarioNivelRepo.save(criaUsuarioNivel());
        assertNotNull(usuarioNivelRepo.findById(99999L).get().getId());
        assertEquals(DESCR_1, usuarioNivel.getDescr());
        usuarioNivel.setAdministrador(true);
        usuarioNivel.setProprietario(true);
        usuarioNivel.setParceiro(true);
        usuarioNivel.setCliente(true);
        usuarioNivel = usuarioNivelRepo.save(usuarioNivel);
        assertTrue(usuarioNivel.getAdministrador());
        assertTrue(usuarioNivel.getProprietario());
        assertTrue(usuarioNivel.getParceiro());
        assertTrue(usuarioNivel.getCliente());
    }

  	@Test
	void testeUsuarioNivelDelete() {
        usuarioNivelRepo.delete(usuarioNivelRepo.save(criaUsuarioNivel()));
        assertFalse(usuarioNivelRepo.existsById(99999L));
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    public UsuarioNivel criaUsuarioNivel(){
        UsuarioNivel usuarioNivel = new UsuarioNivel();
        usuarioNivel.setId(99999L); 
        usuarioNivel.setKey(NIVEL_1);
        usuarioNivel.setDescr(DESCR_1);

        usuarioNivel.setAdministrador(true);
        usuarioNivel.setParceiro(true);
        usuarioNivel.setProprietario(true);
        usuarioNivel.setCliente(true);

        return usuarioNivel;
    }

}
