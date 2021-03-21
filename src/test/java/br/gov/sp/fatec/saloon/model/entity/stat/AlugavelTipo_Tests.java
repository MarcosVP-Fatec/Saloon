package br.gov.sp.fatec.saloon.model.entity.stat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.stat.AlugavelTipoRepository;

@SpringBootTest
@Transactional
@Rollback
public class AlugavelTipo_Tests {
    
    @Autowired
    private AlugavelTipoRepository alugavelTipoRepo;
/*
   	@Test
	public void testeAluvagelTipoSave() {
        alugavelTipoRepo.save(criaAlugavelTipo());
        assertNotNull(alugavelTipoRepo.findByDescr("TESTE_TIPO_ALUGAVEL"));
    }

    @Test
	public void testeAluvagelTipoDelete() {
        alugavelTipoRepo.delete(alugavelTipoRepo.save(criaAlugavelTipo()));
        assertFalse(alugavelTipoRepo.existsByDescr("TESTE_TIPO_ALUGAVEL"));
    }
*/
    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private AlugavelTipo criaAlugavelTipo(){
        AlugavelTipo alugavelTipo = new AlugavelTipo();
        alugavelTipo.setDescr("#TESTE_TIPO_ALUGAVEL");
        return alugavelTipo;
    }

}