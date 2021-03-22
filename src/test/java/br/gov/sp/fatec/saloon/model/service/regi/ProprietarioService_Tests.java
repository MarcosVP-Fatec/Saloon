package br.gov.sp.fatec.saloon.model.service.regi;

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

import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
@Transactional
@Rollback
public class ProprietarioService_Tests {
    
    @Autowired
    private ProprietarioRepository proprietarioRepo;

    @Autowired
    private ProprietarioService proprietarioServiceRepo;

    public ProprietarioService_Tests() throws ParseException {}

    final String APELIDO_1 = "#APELIDO_USUÁRIO_1";
    final String NOME_1    = "#NOME_USUÁRIO_1";
    final String NOME_2    = "#NOME_USUÁRIO_2";

    Date dtNascimento = Data.toDate("12/04/1969");
    Date dtFinal = Data.today();

    @Test
    void testeProprietarioServiceIncluir() {
        proprietarioServiceRepo.inc(APELIDO_1, "#teste@teste.com.br", "ps123", NOME_1, dtNascimento, "99999999999");
        assertTrue(proprietarioRepo.existsByApelido(APELIDO_1));
    }

  	@Test
	void testeProprietarioServiceAlterar() {
        Proprietario prop = proprietarioServiceRepo.inc(APELIDO_1, "#teste@teste.com.br", "ps123", NOME_1, dtNascimento, "99999999999");
        proprietarioServiceRepo.alt(prop.getId(), APELIDO_1, "#teste@teste.com.br", "ps123", NOME_2, dtNascimento, "99999999999");
        proprietarioRepo.flush();
        assertTrue(proprietarioRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
        assertEquals(NOME_2, prop.getNome());
    }    

  	@Test
	void testeProprietarioServiceExcluir() {
        proprietarioServiceRepo.del(proprietarioServiceRepo.inc(APELIDO_1, "#teste@teste.com.br", "ps123", NOME_1, dtNascimento, "99999999999").getId());
        assertFalse(proprietarioRepo.existsByApelido(APELIDO_1));
    }    

}
