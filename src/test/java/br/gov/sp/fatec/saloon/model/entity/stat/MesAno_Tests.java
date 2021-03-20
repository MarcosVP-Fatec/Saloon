package br.gov.sp.fatec.saloon.model.entity.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.MesAnoRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SpringBootTest
@Transactional
public class MesAno_Tests {

    @Autowired
    private MesAnoRepository mesAnoRepo;
    
  	@Test
	void testeMesAnoQtd() {

        assertEquals(12, mesAnoRepo.findAll().size() ); 

    }

}
