package br.gov.sp.fatec.saloon.model.service.stat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.repository.stat.AlugavelTipoRepository;
import br.gov.sp.fatec.saloon.service.stat.AlugavelTipoService;

@SpringBootTest
@Transactional
@Rollback
public class AlugavelTipoService_Tests {
    
    @Autowired
    private AlugavelTipoRepository alugavelTipoRepo;

    @Autowired
    private AlugavelTipoService alugavelTipoServiceRepo;

    final String DESCRICAO_1 = "#TESTE_TP_ALUG";
    final String DESCRICAO_2 = "#TESTE2_TPALUG";

    @Test
    public void testeAlugavelTipoServiceIncluir(){
        alugavelTipoServiceRepo.persist(DESCRICAO_1);
        assertTrue(alugavelTipoRepo.existsByDescr(DESCRICAO_1));
    }

    @Test
    public void testeAlugavelTipoServiceAlterar(){
        AlugavelTipo tipo = alugavelTipoServiceRepo.persist(DESCRICAO_1);
        alugavelTipoServiceRepo.persist(tipo.getId(), DESCRICAO_2);
        assertFalse(alugavelTipoRepo.existsByDescr(DESCRICAO_1));
        assertTrue(alugavelTipoRepo.existsByDescr(DESCRICAO_2));
    }

    @Test
    public void testeAlugavelTipoServiceExcluir(){
        alugavelTipoServiceRepo.persist(DESCRICAO_1);
        alugavelTipoServiceRepo.delete(DESCRICAO_1);
        assertFalse(alugavelTipoRepo.existsByDescr(DESCRICAO_1));
    }

}

