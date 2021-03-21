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

@SpringBootTest
@Transactional
@Rollback
public class ContratoMotivoService_Tests {
    
    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

    @Autowired
    private ContratoMotivoService contratoMotivoServiceRepo;

    @Test
    public void testeContratoMotivoServiceIncluir(){
        contratoMotivoServiceRepo.inc("#TESTE_CTR_MOTIVO");
        assertTrue(contratoMotivoRepo.existsByDescr("#TESTE_CTR_MOTIVO"));
    }

    @Test
    public void testeContratoMotivoServiceAlterar(){
        ContratoMotivo ctr = contratoMotivoServiceRepo.inc("#TESTE_CTR_MOTIVO");
        contratoMotivoServiceRepo.alt(ctr.getId(),"#TESTE2_CTR_MOTIVO");
        assertFalse(contratoMotivoRepo.existsByDescr("#TESTE_CTR_MOTIVO"));
        assertTrue(contratoMotivoRepo.existsByDescr("#TESTE2_CTR_MOTIVO"));
    }

}

