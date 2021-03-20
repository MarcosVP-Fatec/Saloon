package br.gov.sp.fatec.saloon.model.entity.stat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.ContratoMotivoRepository;

@SpringBootTest
@Transactional
@Rollback
public class ContratoMotivo_Tests {

    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

    @Test
    public void testContratoMotivoSalvar(){
        this.criaContratoMotivo();
        assertNotNull(contratoMotivoRepo.findByDescr("#TESTE_MOTIVO").getId());
    }

  	@Test
	public void testeContratoMotivoAlterar() {
        ContratoMotivo ctr = this.criaContratoMotivo();
        ctr.setDescr("#TESTE2_MOTIVO");
        contratoMotivoRepo.save(ctr);
        assertNotNull(contratoMotivoRepo.findByDescr("#TESTE2_MOTIVO").getId());
    }

  	@Test
	public void testeContratoMotivoExcluir() {
        ContratoMotivo ctr = this.criaContratoMotivo();
        contratoMotivoRepo.save(ctr);
        contratoMotivoRepo.delete(ctr);
        assertNull(contratoMotivoRepo.findByDescr("#TESTE_MOTIVO"));
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private ContratoMotivo criaContratoMotivo(){
        ContratoMotivo ctr = new ContratoMotivo();
        ctr.setDescr("#TESTE_MOTIVO");
        return contratoMotivoRepo.save(ctr);
    }

}
