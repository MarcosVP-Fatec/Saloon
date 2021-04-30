package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.exception.RegistroJaExisteException;
import br.gov.sp.fatec.saloon.exception.RegistroNaoEncontratoException;
import br.gov.sp.fatec.saloon.exception.RegistroNaoExcluidoException;
import br.gov.sp.fatec.saloon.model.repository.regi.ParametroRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.ParametroService;

@SpringBootTest
@Transactional
@Rollback
public class Parametro_Tests {

    @Autowired
    private ParametroRepository parametroRepo;
    
    @Autowired
    private ParametroService	parametroService;

    public Parametro_Tests() throws ParseException {}

    final String    COD_1       = "#TESTE_PARÂMETRO__1";
    final String    DESCR_1     = "#TESTE_1_DESCR_PARÂMETRO";
    final String    DESCR_2     = "#TESTE_2_DESCR_PARÂMETRO";
    final Date      DATA_1      = Data.toDate("12/04/1969");
    final Date      DATA_2      = Data.toDate("31/12/2021");

    @Test
	void testeParametroCrudNumero() {
    	
    	//Inclusão
    	Parametro parametro;
    	for (int i = 0; i < 2; i++) {
    		try {
            	parametro = parametroService.inc(COD_1, DESCR_1, 12.0);
                assertEquals(12, parametroRepo.parametroNumero(COD_1));
                assertEquals(12, parametro.getNumero());
                assertEquals(COD_1, parametro.getCod());
                assertEquals(DESCR_1, parametro.getDescricao());
                assertEquals('N', parametro.getTipo());
    		} catch (RegistroJaExisteException e) {
    			assertEquals("Tentativa de inclusão de parâmetro que já existe -> " + COD_1 , e.getMessage() );
			}
    	}

    	//Alteração
    	int qtd = parametroRepo.findByDescricaoContainsIgnoreCase(DESCR_2).size();
    	for (int i = 0; i < 2; i++) {
    		try {
        		parametro = parametroService.alt(COD_1, DESCR_2, 100);
        		assertEquals(qtd + 1, parametroRepo.findByDescricaoContainsIgnoreCase(DESCR_2).size());
                assertEquals(100, parametroRepo.parametroNumero(COD_1));
                assertEquals(100, parametro.getNumero());
                assertEquals(DESCR_2, parametro.getDescricao());
                
                // Exclusão bem sucedida
                parametroService.del(COD_1);
                assertFalse(parametroRepo.existsByCod(COD_1));
                
			} catch (RegistroNaoEncontratoException e) {
    			assertEquals("Tentativa de alteração de parâmetro que não existe -> " + COD_1 , e.getMessage() );
			}
    	}
    	
    	//Exclusão mal sucedida
    	parametro = parametroService.inc(COD_1, DESCR_1, 12.0);
    	for (int i = 0; i < 2; i++) {
			try {
				parametroService.del(COD_1);
				assertFalse(parametroRepo.existsByCod(COD_1));
			} catch (RegistroNaoExcluidoException e) {
				//TODO = Para quando existir uma fk que permita esta validação.
				//assertEquals("Tentativa de exclusão de parâmetro mal suscedida -> " + COD_1 , e.getMessage() );    			
			}
    	}
    	parametroService.del(COD_1);
    }
    
    @Test
	void testeParametroCrudTexto() {

    	//Inclusão
    	Parametro parametro;
    	for (int i = 0; i < 2; i++) {
    		try {
    			parametro = parametroService.inc(COD_1, DESCR_1, "umdois");
                assertEquals("umdois", parametroRepo.parametroTexto(COD_1));
                assertEquals("umdois", parametro.getStr());
    		} catch (RegistroJaExisteException e) {
    			assertEquals("Tentativa de inclusão de parâmetro que já existe -> " + COD_1 , e.getMessage() );
			}
    	}

    	//Alteração
    	int qtd = parametroRepo.findByDescricaoContainsIgnoreCase(DESCR_2).size();
    	for (int i = 0; i < 2; i++) {
    		try {
        		parametro = parametroService.alt(COD_1, DESCR_2, "#TEXTO ALTERNATIVO");
        		assertEquals(qtd + 1, parametroRepo.findByDescricaoContainsIgnoreCase(DESCR_2).size());
                assertEquals("#TEXTO ALTERNATIVO", parametroRepo.parametroTexto(COD_1));
                assertEquals("#TEXTO ALTERNATIVO", parametro.getStr());
                
                // Exclusão bem sucedida
                parametroService.del(COD_1);
                assertFalse(parametroRepo.existsByCod(COD_1));
                
			} catch (RegistroNaoEncontratoException e) {
    			assertEquals("Tentativa de alteração de parâmetro que não existe -> " + COD_1 , e.getMessage() );
			}
    	}
    	
    	//Exclusão mal sucedida
    	parametro = parametroService.inc(COD_1, DESCR_1, 12.0);
    	for (int i = 0; i < 2; i++) {
			try {
				parametroService.del(COD_1);
				assertFalse(parametroRepo.existsByCod(COD_1));
			} catch (RegistroNaoExcluidoException e) {
				//TODO = Para quando existir uma fk que permita esta validação.
				//assertEquals("Tentativa de exclusão de parâmetro mal suscedida -> " + COD_1 , e.getMessage() );    			
			}
    	}
    	parametroService.del(COD_1);
    }

    @Test
	void testeParametroIncData() {
    	Parametro parametro = parametroService.inc(COD_1, DESCR_1, DATA_1);
        assertEquals(DATA_1, parametroRepo.parametroData(COD_1));
    	assertEquals(DATA_1, parametro.getData());
    }

    @Test
	void testeParametroIncLogico() {
    	Parametro parametro = parametroService.inc(COD_1, DESCR_1, false);
        assertEquals(false, parametroRepo.parametroLogico(COD_1));
        assertEquals(false, parametro.isLogico());
    }
/*
    @Test
	void testeParametroDelete() {
        parametroRepo.delete(parametroServiceRepo.inc(COD_1, DESCR_1, false));
        assertFalse(parametroRepo.existsByCod(COD_1));
    }
    */

}