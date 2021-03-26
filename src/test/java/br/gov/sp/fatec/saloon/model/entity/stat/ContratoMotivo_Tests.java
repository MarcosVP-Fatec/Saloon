package br.gov.sp.fatec.saloon.model.entity.stat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.stat.ContratoMotivoRepository;

@SpringBootTest
@Transactional
@Rollback
public class ContratoMotivo_Tests {

    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

    final String DESCR_1    =   "#TESTE_CTR_MOT_1";
    final String DESCR_2    =   "#TESTE_CTR_MOT_2";

    @Test
    public void testContratoMotivoSalvar(){
        ContratoMotivo ctr = this.criaContratoMotivo();
        assertNotNull(ctr);
        assertNotNull(ctr.getId());
        assertTrue(contratoMotivoRepo.existsByDescr(DESCR_1));
    }

  	@Test
	public void testeContratoMotivoAlterar() {
        ContratoMotivo ctr = this.criaContratoMotivo();
        ctr.setDescr(DESCR_2);
        contratoMotivoRepo.save(ctr);
        assertNotNull(contratoMotivoRepo.findByDescr(DESCR_2).getId());
        assertFalse(contratoMotivoRepo.existsByDescr(DESCR_1));
        assertTrue(contratoMotivoRepo.existsByDescr(DESCR_2));
    }

  	@Test
	public void testeContratoMotivoExcluir() {
        ContratoMotivo ctr = this.criaContratoMotivo();
        contratoMotivoRepo.save(ctr);
        contratoMotivoRepo.delete(ctr);
        assertNull(contratoMotivoRepo.findByDescr("#TESTE_MOTIVO"));
    }

  	@Test
	public void testeDeContratoMotivoLista() {
        assertTrue(contratoMotivoRepo.listaCompleta().size() > 0);
    }

    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private ContratoMotivo criaContratoMotivo(){
        ContratoMotivo ctr = new ContratoMotivo();
        ctr.setDescr(DESCR_1);
        return contratoMotivoRepo.save(ctr);
    }


}
