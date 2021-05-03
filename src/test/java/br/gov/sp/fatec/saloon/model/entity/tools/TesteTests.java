package br.gov.sp.fatec.saloon.model.entity.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.gov.sp.fatec.saloon.model.tool.Texto;

@SpringBootTest
public class TesteTests {
	
	@Test
	void testeTextos() {
		assertEquals("ab", Texto.left("abcdef", 2) );
		assertEquals("abcdef", Texto.left("abcdef", 10) );
		assertEquals("ef", Texto.right("abcdef", 2) );
		assertEquals("abcdef", Texto.right("abcdef", 10) );
		
		assertEquals("   abcdef  ", Texto.padC("abcdef", 11) );
		assertEquals("##abcdef##", Texto.padC("abcdef", 10,"#") );
		assertEquals("##abcdef##", Texto.padC("abcdef", 10,'#') );
		assertEquals("abcdef", Texto.padC("abcdef", 5,'#') );
		
		assertEquals("---abcdef---", Texto.padC("abcdef", 12,"-+"));
		assertEquals("----abcdef---", Texto.padC("abcdef", 13,"-+"));

		assertEquals("   abcdef", Texto.padL("abcdef", 9) );
		assertEquals("###abcdef", Texto.padL("abcdef", 9,"#") );
		assertEquals("###abcdef", Texto.padL("abcdef", 9,'#') );
	
		assertEquals("abcdef   ", Texto.padR("abcdef", 9) );
		assertEquals("abcdef"   , Texto.padR("abcdef", 6) );
		assertEquals("abcdef###", Texto.padR("abcdef", 9,"#") );
		assertEquals("abcdef###", Texto.padR("abcdef", 9,'#') );
		assertEquals("abc", Texto.padR("abcdef", 3) );
		
		assertEquals("abcdefg", Texto.concatenarChar('a','b','c','d','e','f','g'));
		
		assertEquals("mmmmmmm", Texto.replicate('m', 7));
		assertEquals("mmmmmmm", Texto.replicate("m", 7));
		assertEquals("vpvpvpvpvpvpvp", Texto.replicate("vp", 7));
		
		assertEquals("00123", Texto.strZero(123, 5));
		assertEquals("00012", Texto.strZero("12", 5));
		
		assertEquals("português-BR", Texto.Trad("português-BR"));
		
		assertTrue(Texto.sohDigitos("0123456789"));
		assertFalse(Texto.sohDigitos("0123456789X"));

	}

}
