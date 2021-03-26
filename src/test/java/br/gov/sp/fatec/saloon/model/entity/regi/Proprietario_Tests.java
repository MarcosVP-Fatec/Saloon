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

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
@Transactional
@Rollback
public class Proprietario_Tests {

    @Autowired
    private ProprietarioRepository  proprietarioRepo;

    @Autowired
    private UsuarioNivelRepository  usuarioNivelRepo;

    public Proprietario_Tests() throws ParseException {}

    final String    APELIDO_1   = "#TESTE_PROPRIETARIO_1";
    final String    NOME_1      = "#TESTE_1_NOME_PROPRIETÁRIO";
    final String    NOME_2      = "#TESTE_2_NOME_PROPRIETÁRIO";
    final Date      DTNASC_1    = Data.toDate("12/04/1969");
    final Date      DFINAL      = Data.today();
    final String    EMAIL_1     = "#teste_1_proprietário@saloon.br";
    final String    CPF_1       = "99999999999";
    final String    SENHA_1     = "#SENHA_PARCEIRO_1";

    @Test
    void testeProprietarioIncluir() {
        proprietarioRepo.save(this.criaProprietarioTeste());
        assertTrue(proprietarioRepo.existsByApelido(APELIDO_1));
    }

    @Test
    void testeProprietarioAlterar() {
        Proprietario prop = proprietarioRepo.save(this.criaProprietarioTeste());
        assertTrue(proprietarioRepo.existsByApelido(APELIDO_1));
        assertFalse(proprietarioRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
        prop.setNome(NOME_2);
        proprietarioRepo.save(prop);
        assertTrue(proprietarioRepo.findByNomeContainsIgnoreCase(NOME_2).size() > 0);
    }

  	@Test
	void testeProprietarioExcluir() throws ParseException {
        proprietarioRepo.delete(proprietarioRepo.save(this.criaProprietarioTeste()));
        assertFalse(proprietarioRepo.existsByApelido(APELIDO_1));
    }

    /*
    * Método padrão de criação do proprietário para testes 
    */
    private Proprietario criaProprietarioTeste(){

        Proprietario proprietario = new Proprietario();
        UsuarioNivel usuarioNivel = usuarioNivelRepo.buscarPorId(3L);

        proprietario.setApelido(APELIDO_1);
        proprietario.setEmail(EMAIL_1);
        proprietario.setSenha(SENHA_1);
        proprietario.setNome(NOME_1);
        proprietario.setDtNascimento(DTNASC_1);
        proprietario.setCpf(CPF_1);
        proprietario.setUsuarioNivel(usuarioNivel);
        return proprietario;
    }
}
