package br.gov.sp.fatec.saloon.model.service.stat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.repository.stat.ContratoMotivoRepository;
import br.gov.sp.fatec.saloon.service.stat.ContratoMotivoService;

@SpringBootTest
@Transactional
@Rollback
public class ContratoMotivoService_Tests {
    
    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

    @Autowired
    private ContratoMotivoService contratoMotivoServiceRepo;

    final String    DESCR_1     =   "#TESTE_CTR_MOTIVO_1";
    final String    DESCR_2     =   "#TESTE_CTR_MOTIVO_2";

    @Test
    public void testeContratoMotivoServiceIncluir(){
        contratoMotivoServiceRepo.persist(DESCR_1);
        assertTrue(contratoMotivoRepo.existsByDescr(DESCR_1));
    }

    @Test
    public void testeContratoMotivoServiceAlterar(){
        ContratoMotivo ctr = contratoMotivoServiceRepo.persist(DESCR_1);
        contratoMotivoServiceRepo.persist(ctr.getId(),DESCR_2);
        assertFalse(contratoMotivoRepo.existsByDescr(DESCR_1));
        assertTrue(contratoMotivoRepo.existsByDescr(DESCR_2));
    }

}

