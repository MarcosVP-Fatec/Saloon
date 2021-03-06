package br.gov.sp.fatec.saloon.model.service.regi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.ProprietarioService;

@SpringBootTest
@Transactional
@Rollback
public class ProprietarioService_Tests {

  @Autowired
  private ProprietarioRepository proprietarioRepo;

  @Autowired
  private ProprietarioService proprietarioServiceRepo;

  public ProprietarioService_Tests() throws ParseException {
  }

  final String APELIDO_1 = "#TESTE_PROPRIETARIO_1";
  final String NOME_1 = "#TESTE_1_NOME_PROPRIETÁRIO";
  final String NOME_2 = "#TESTE_2_NOME_PROPRIETÁRIO";
  final Date DTNASC_1 = Data.toDate("12/04/1969");
  final Date DFINAL = Data.today();
  final String EMAIL_1 = "#teste_1_proprietário@saloon.br";
  final String CPF_1 = "99999999999";
  final String SENHA_1 = "#SENHA_PARCEIRO_1";

  // @Test
  // void testeTransacao(){
  // this.criaProprietarioTeste();
  // System.out.println("####################################################################################");
  // System.out.println("####################################################################################");
  // System.out.println("####################################################################################");
  // System.out.println("####################################################################################");
  // System.out.println("####################################################################################");
  // System.out.println("####################################################################################");
  // System.out.println("> > > > > > > > > > > > " +
  // proprietarioRepo.existsByApelido(APELIDO_1));
  // assertTrue(proprietarioRepo.existsByApelido(APELIDO_1));
  // }

  @Test
  void testeProprietarioServiceIncluir() {
    this.criaProprietarioTeste();
    assertTrue(proprietarioRepo.existsByApelido(APELIDO_1));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testeProprietarioServiceAlterar() {
    int qtd = proprietarioRepo.findByNomeContainsIgnoreCase(NOME_2).size();
    Proprietario prop = this.criaProprietarioTeste();
    proprietarioServiceRepo.persist(prop.getId(), APELIDO_1, EMAIL_1, SENHA_1, NOME_2 // <<<<<<<<<< Campo alterado
        , DTNASC_1, CPF_1);

    proprietarioRepo.flush();
    assertEquals(qtd + 1, proprietarioRepo.findByNomeContainsIgnoreCase(NOME_2).size());
    assertEquals(NOME_2, prop.getNome());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testeProprietarioServiceExcluir() {
    proprietarioServiceRepo.delete(this.criaProprietarioTeste().getApelido());
    assertFalse(proprietarioRepo.existsByApelido(APELIDO_1));
  }

  @Test
  void testeProprietarioCampos() {
    Proprietario prop = this.criaProprietarioTeste();
    prop.setDtInicio();
    assertEquals(Data.today(), prop.getDtInicio());
    prop.setDtInicio(DTNASC_1);
    assertEquals(DTNASC_1, prop.getDtInicio());
    prop.setDtLimite(DTNASC_1);
    assertEquals(DTNASC_1, prop.getDtLimite());
    prop.setDtLimite(12);
    assertEquals(Data.addD(Data.today(), 12), prop.getDtLimite());

  }

  /*
   * Método padrão de criação do proprietário para testes
   */
  private Proprietario criaProprietarioTeste() {
    return proprietarioServiceRepo.inc(APELIDO_1, EMAIL_1, SENHA_1, NOME_1, DTNASC_1, CPF_1);
  }
}
