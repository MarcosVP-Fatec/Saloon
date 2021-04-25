package br.gov.sp.fatec.saloon.model.entity.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.stat.MesAnoRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SpringBootTest
@Transactional
public class MesAno_Tests {

    @Autowired
    private MesAnoRepository mesAnoRepo;
    
  	@Test
	void testeMesAnoQtd() {
        //assertTrue( mesAnoRepo.count() == 12L );
        assertEquals(12L, mesAnoRepo.count());
    }

  	@Test
	void testeMesAnoGS() {
        MesAno mesAno = mesAnoRepo.findById(4L).get();
        assertEquals("Abril", mesAno.getDescr());
        assertEquals(4L, mesAno.getId());
        mesAno.setId(99L);
        mesAno.setDescr("Inválido");
        assertEquals("Inválido", mesAno.getDescr());
        assertEquals(99L, mesAno.getId());
        mesAno.setNumero("99");
        assertEquals("99", mesAno.getNumero());
    }

}
