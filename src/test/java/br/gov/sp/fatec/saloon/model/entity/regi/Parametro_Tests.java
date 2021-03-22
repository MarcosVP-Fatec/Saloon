package br.gov.sp.fatec.saloon.model.entity.regi;

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

import br.gov.sp.fatec.saloon.model.repository.regi.ParametroRepository;
import br.gov.sp.fatec.saloon.model.service.regi.ParametroService;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
@Transactional
@Rollback
public class Parametro_Tests {

    @Autowired
    private ParametroRepository parametroRepo;

    @Autowired
    private ParametroService    parametroServiceRepo;

    public Parametro_Tests() throws ParseException {}

    final String    COD_1       = "#TESTE_PARÂMETRO__1";
    final String    DESCR_1     = "#TESTE_1_DESCR_PARÂMETRO";
    final String    DESCR_2     = "#TESTE_2_DESCR_PARÂMETRO";
    final Date      DATA_1      = Data.toDate("12/04/1969");

    @Test
	void testeParametroIncNumero() {
        parametroServiceRepo.inc(COD_1, DESCR_1, 12);
        parametroRepo.flush();
        assertEquals(12, parametroRepo.parametroNumero(COD_1));
    }
    @Test
	void testeParametroIncTexto() {
        parametroServiceRepo.inc(COD_1, DESCR_1, "umdois");
        parametroRepo.flush();
        assertEquals("umdois", parametroRepo.parametroTexto(COD_1));
    }

    @Test
	void testeParametroIncData() {
        parametroServiceRepo.inc(COD_1, DESCR_1, DATA_1);
        parametroRepo.flush();
        assertEquals(DATA_1, parametroRepo.parametroData(COD_1));
    }

    @Test
	void testeParametroIncLogico() {
        parametroServiceRepo.inc(COD_1, DESCR_1, false);
        parametroRepo.flush();
        assertEquals(false, parametroRepo.parametroLogico(COD_1));
    }

    @Test
    void testeParametroAltNumero() {
        Parametro par = parametroServiceRepo.inc(COD_1, DESCR_1, DATA_1);
        assertFalse(parametroRepo.findByDescricaoContainsIgnoreCase(DESCR_2).size() > 0);
        par.setDescricao(DESCR_2);
        parametroRepo.save(par);
        parametroRepo.flush();
        assertTrue(parametroRepo.findByDescricaoContainsIgnoreCase(DESCR_2).size() > 0);
    }

    @Test
	void testeParametroDelete() {
        parametroRepo.delete(parametroServiceRepo.inc(COD_1, DESCR_1, false));
        assertFalse(parametroRepo.existsByCod(COD_1));
    }

}

    

