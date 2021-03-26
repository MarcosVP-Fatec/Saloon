package br.gov.sp.fatec.saloon.model.repository.stat;

import java.util.Set;

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
     * @apiNote buscarPorId(Long)
     *          Pesquisa por Id
     * @param id (Long)
     * @return ContratoMotivo
     */
    @Query("select c from ContratoMotivo c where c.id = ?1")
    public ContratoMotivo buscarPorId(Long id);
    
    /**
     * @apiNote findByDescr --> Pesquisa por Descr (Exata)
     * @param descr (String)
     * @return ContratoMotivo
     */
    public ContratoMotivo findByDescr(String descr);

    /**
     * @apiNote findByDescrContainsIgnoreCase --> por Descr (Contendo)
     * @param descr (String)
     * @return Set<ContratoMotivo>
     */
    public Set<ContratoMotivo> findByDescrContainsIgnoreCase(String descr);
    
    /**
     * @apiNote existsByDescr() Pesquisa por Descr (Exato)
     * @param descr
     * @return boolean
     */
    public boolean existsByDescr(String descr);

    /**
     * @apiNote Lista total de Motivos de Contrato
     * @return Set<ContratoMotivo>
     */
    @Query("select c from ContratoMotivo c order by c.id")
     public Set<ContratoMotivo> listaCompleta();

}


