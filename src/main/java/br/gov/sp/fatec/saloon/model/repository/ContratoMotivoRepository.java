package br.gov.sp.fatec.saloon.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;

/**
 * @apiNote Dao da entidade ContratoMotivo
 * 
 *          I Spring fornece um Query Method Meu resultado pode voltar uma lista
 *          ou somente um O nome do método tem que começar com "findBy" +
 *          atributo Ex. para o atributo "nome" findByNome (Que seria o mesmo
 *          que findByNomeEquals) Ex. para usar o like findByNomeContains(String
 *          nome); findByNomeContainsIgnoreCase(String nome);
 * 
 */
public interface ContratoMotivoRepository extends JpaRepository<ContratoMotivo, Long>{

    /**
     * @apiNote Pesquisa por Id
     * @param id
     * @return ContratoMotivo
     */
    @Query("select c from ContratoMotivo c where c.id = ?1")
    public ContratoMotivo buscaPorId(Long id);
    
    /**
     * @apiNote Pesquisa por Descr (Exato)
     * @param descr
     * @return ContratoMotivo
     */
    public ContratoMotivo findByDescr(String descr);

    /**
     * @apiNote Pesquisa por Descr (Contendo)
     * @param descr
     * @return List<ContratoMotivo>
     */
    public List<ContratoMotivo> findByDescrContainsIgnoreCase(String descr);
    
}


