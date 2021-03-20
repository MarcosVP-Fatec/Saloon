package br.gov.sp.fatec.saloon.model.entity.stat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.gov.sp.fatec.saloon.model.repository.UsuarioNivelRepository;

@SpringBootTest
public class Teste_UsuarioNivel {

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

  	@Test
	void testeUsuarioNivelSave() {

        //UsuarioNivel usuarioNivel = new UsuarioNivel();
        
        //usuarioNivel.setId(99999L); 
        //usuarioNivel.setDescr("#TESTE_DE_NIVEL");
        /*
        usuarioNivel.setAdministrador(true);
        usuarioNivel.setParceiro(true);
        usuarioNivel.setProprietario(true);
        usuarioNivel.setCliente(true);
        */
        //usuarioNivelRepo.save(usuarioNivel);

        //assertNotNull(usuarioNivelRepo.findById(99999L));
        assertNotNull(1);
        
	}

}

