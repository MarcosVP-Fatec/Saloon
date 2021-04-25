package br.gov.sp.fatec.saloon.model.entity.stat;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

   	@Test
    public void testeAluvagelTipoSalvar() {
        alugavelTipoRepo.save(criaAlugavelTipo());
        //assertTrue(alugavelTipoRepo.existsByDescr("#TESTE_TIPO_ALUGÁVEL"));
    }

    /*

    @Test
	public void testeAluvagelTipoExcluir() {
        alugavelTipoRepo.delete(criaAlugavelTipo());
        assertFalse(alugavelTipoRepo.existsByDescr("#TESTE_TIPO_ALUGÁVEL"));
    }
*/
    /*
     * Método padrão de criação de uma entidade completa para testes.
     */
    private AlugavelTipo criaAlugavelTipo(){
        AlugavelTipo alugavelTipo = new AlugavelTipo();
        alugavelTipo.setDescr("#TESTE_TIPO_ALUGÁVEL");
        alugavelTipoRepo.save(alugavelTipo);
        return alugavelTipo;
    }

}