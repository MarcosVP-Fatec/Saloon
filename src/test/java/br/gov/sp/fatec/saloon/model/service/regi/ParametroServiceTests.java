package br.gov.sp.fatec.saloon.model.service.regi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.exception.RegistroJaExisteException;
import br.gov.sp.fatec.saloon.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.repository.regi.ParametroRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.ParametroService;

@SpringBootTest
@Transactional
@Rollback
public class ParametroServiceTests {
	
	final static String MSG_INC = "Tentativa de inclusão de registro que já existe: ";
	final static String MSG_ALT = "Tentativa de alteração de registro que não existe: ";

	@Autowired
	private ParametroRepository		parametroRepo;
	
	@Autowired
	private ParametroService		parametroService;
	
	final static String COD_1		=	"#COD_TESTE_1";
	final static String COD_2		=	"#COD_TESTE_2";
	final static String COD_3		=	"#COD_TESTE_3";
	final static String DESCR_1	=	"#DESCR_1";
	final static String DESCR_2	=	"#DESCR_2";
	final static String DESCR_3	=	"#DESCR_3";
	final static double NUMERO_1	=	12.34;
	final static double NUMERO_2	=	100.0;
	final static String TEXTO_1	=	"Oi mundo!";
	final static String TEXTO_2	=	"Hello World!";
	final static Date	 DATA_1		=	Data.today();
	final static Date	 DATA_2 	=	Data.addD(Data.today(),1);
	
	@Test
	void testeParametroIncAltDelNumeroObjeto() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		Parametro parametro = new Parametro(COD_1, DESCR_1, NUMERO_1);
		parametroService.inc(parametro);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Del
		parametroService.del(COD_1);
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametroService.inc(COD_1, DESCR_1, NUMERO_1);
		parametro.setDescricao(DESCR_2);
		parametro.setNumero(100.0);
		parametro.setTipo('?');
		parametroService.alt(parametro);
		assertEquals(DESCR_2,parametroRepo.findByCod(COD_1).getDescricao());
		assertEquals(NUMERO_2,parametroRepo.findByCod(COD_1).getNumero());
		
		//Exception inc Objeto
		parametro.setCod(COD_2);
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(parametro);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Objeto
		for (String cod : Arrays.asList(COD_1,COD_3)) {
			try {
				parametro.setCod(cod);
				parametroService.alt(parametro);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
			
		}
		
	}

	@Test
	void testeParametroIncAltDelNumeroParametros() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		parametroService.inc(COD_1, DESCR_1, NUMERO_1);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametroService.alt(COD_1, DESCR_2, NUMERO_2);
		Parametro parametro = parametroRepo.findByCod(COD_1);
		assertEquals(DESCR_2,parametro.getDescricao());
		assertEquals(NUMERO_2,parametro.getNumero());
		
		//Exception inc Parâmetros
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(COD_2, DESCR_1, NUMERO_1);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Parâmetros
		for (String cod : Arrays.asList(COD_1,COD_3)) {
			try {
				parametroService.alt(cod, DESCR_1, NUMERO_1);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
			
		}

	}

	@Test
	void testeParametroIncAltDelTextoObjeto() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		Parametro parametro = new Parametro(COD_1, DESCR_1, TEXTO_1);
		parametroService.inc(parametro);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Del
		parametroService.del(COD_1);
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametroService.inc(COD_1, DESCR_1, TEXTO_1);
		parametro.setDescricao(DESCR_2);
		parametro.setStr(TEXTO_2);
		parametroService.alt(parametro);
		assertEquals(DESCR_2,parametroRepo.findByCod(COD_1).getDescricao());
		assertEquals(TEXTO_2,parametroRepo.findByCod(COD_1).getStr());
		
		//Exception inc Objeto
		parametro.setCod(COD_2);
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(parametro);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Objeto
		for (String cod : Arrays.asList(COD_1,COD_3)) {
			try {
				parametro.setCod(cod);
				parametroService.alt(parametro);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
			
		}

	}

	@Test
	void testeParametroIncAltDelTextoParametros() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		parametroService.inc(COD_1, DESCR_1, TEXTO_1);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametroService.alt(COD_1, DESCR_2, TEXTO_2);
		Parametro parametro = parametroRepo.findByCod(COD_1);
		assertEquals(DESCR_2,parametro.getDescricao());
		assertEquals(TEXTO_2,parametro.getStr());

		//Exception inc Parâmetros
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(COD_2, DESCR_1, TEXTO_1);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Parâmetros
		for (String cod : Arrays.asList(COD_2,COD_3)) {
			try {
				parametroService.alt(cod, DESCR_1, TEXTO_1);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
			
		}
		
	}

	@Test
	void testeParametroIncAltDelDataObjeto() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		Parametro parametro = new Parametro(COD_1, DESCR_1, DATA_1);
		parametroService.inc(parametro);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametro.setDescricao(DESCR_2);
		parametro.setData(DATA_2);
		parametroService.alt(parametro);
		assertEquals(DESCR_2,parametroRepo.findByCod(COD_1).getDescricao());
		assertEquals(DATA_2,parametroRepo.findByCod(COD_1).getData());
		
		//Exception inc Objeto
		parametro.setCod(COD_2);
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(parametro);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Objeto
		for (String cod : Arrays.asList(COD_1,COD_3)) {
			try {
				parametro.setCod(cod);
				parametroService.alt(parametro);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
			
		}

	}

	@Test
	void testeParametroIncAltDelDataParametros() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		parametroService.inc(COD_1, DESCR_1, DATA_1);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametroService.alt(COD_1, DESCR_2, DATA_2);
		Parametro parametro = parametroRepo.findByCod(COD_1);
		assertEquals(DESCR_2,parametro.getDescricao());
		assertEquals(DATA_2,parametro.getData());
		
		//Exception inc Parâmetros
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(COD_2, DESCR_1, DATA_1);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Parâmetros
		for (String cod : Arrays.asList(COD_2,COD_3)) {
			try {
				parametroService.alt(cod, DESCR_1, DATA_1);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
			
		}
	}

	@Test
	void testeParametroIncAltDelLogicoObjeto() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		Parametro parametro = new Parametro(COD_1, DESCR_1, true);
		parametroService.inc(parametro);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametro.setDescricao(DESCR_2);
		parametro.setLogico(false);
		parametroService.alt(parametro);
		assertEquals(DESCR_2,parametroRepo.findByCod(COD_1).getDescricao());
		assertEquals(false,parametroRepo.findByCod(COD_1).isLogico());
		
		//Exception inc Objeto
		parametro.setCod(COD_2);
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(parametro);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Objeto
		for (String cod : Arrays.asList(COD_1,COD_3)) {
			try {
				parametro.setCod(cod);
				parametroService.alt(parametro);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
			
		}

	}

	@Test
	void testeParametroIncAltDelLogicoParametros() {
		
		assertFalse(parametroRepo.existsByCod(COD_1));
		
		//Inc
		parametroService.inc(COD_1, DESCR_1, true);
		assertTrue(parametroRepo.existsByCod(COD_1));
		
		//Alt
		parametroService.alt(COD_1, DESCR_2, false);
		Parametro parametro = parametroRepo.findByCod(COD_1);
		assertEquals(DESCR_2,parametro.getDescricao());
		assertEquals(false,parametro.isLogico());
		
		//Exception inc Parâmetros
		for (int i = 0; i < 2; i++) {
			try {
				parametroService.inc(COD_2, DESCR_1, true);
			} catch (RegistroJaExisteException e) {
				assertEquals(MSG_INC + COD_2, e.getMessage() ); 
			}
		}
		
		//Exception alt Parâmetros
		for (String cod : Arrays.asList(COD_2,COD_3)) {
			try {
				parametroService.alt(cod, DESCR_1, true);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals(MSG_ALT + cod, e.getMessage() ); 
			}
		}

	}
	
	@Test
	void testeParametroBusca() {

        parametroService.inc(COD_1, DESCR_1, true);
		for (String cod : Arrays.asList(COD_1,COD_2)) {
			try {
				parametroService.buscarPorCod(cod);
			} catch (RegistroNaoEncontradoException e) {
				assertEquals("Código de Parâmetro não encontrado: " + COD_2, e.getMessage() ); 
			}
		}

	}

}
