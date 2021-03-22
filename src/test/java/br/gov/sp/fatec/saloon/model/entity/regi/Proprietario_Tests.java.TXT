package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
@Transactional
@Rollback
public class Proprietario_Tests {

    @Autowired
    private ProprietarioRepository proprietarioRepo;

    final String APELIDO_1 = "#TESTE_PROPRIETARIO_1";
    final String NOME_1 = "#TESTE_PROP_NOME_1";
    final String NOME_2 = "#TESTE_PROP_NOME_2";

    @Test
    void testeProprietarioIncluir() throws ParseException {
        proprietarioRepo.save(this.criaProprietario());
        assertTrue(proprietarioRepo.existsByApelido(APELIDO_1));
    }

    @Test
    void testeProprietarioAlterar() throws ParseException {
        Proprietario prop = proprietarioRepo.save(this.criaProprietario());
        prop.setNome(NOME_2);
        proprietarioRepo.save(prop);
        assertTrue(proprietarioRepo.existsByApelido(APELIDO_1));
        assertTrue(proprietarioRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
    }

  	@Test
	void testeProprietarioExcluir() throws ParseException {
        proprietarioRepo.delete(proprietarioRepo.save(this.criaProprietario()));
        assertFalse(proprietarioRepo.existsByApelido(APELIDO_1));
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private Proprietario criaProprietario() throws ParseException {
        
        Proprietario proprietario = new Proprietario();
        
        Date dtNascimento = Data.toDate("12/04/1969");

        proprietario.setApelido(APELIDO_1);
        proprietario.setEmail("#teste@teste.com.br");
        proprietario.setSenha("pw123");
        proprietario.setNome(NOME_1);
        proprietario.setDtNascimento(dtNascimento);
        proprietario.setCpf("99999999999");
        proprietario.setUsuarioNivel(2L);

        return proprietarioRepo.save(proprietario);
    }

}
