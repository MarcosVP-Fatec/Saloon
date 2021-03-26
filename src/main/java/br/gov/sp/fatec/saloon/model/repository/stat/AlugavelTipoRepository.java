package br.gov.sp.fatec.saloon.model.repository.stat;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

/**
 * @apiNote Dao da entidade AlugavelTipo
 * 
 *          I Spring fornece um Query Method Meu resultado pode voltar uma lista
 *          ou somente um O nome do método tem que começar com "findBy" +
 *          atributo Ex. para o atributo "nome" findByNome (Que seria o mesmo
 *          que findByNomeEquals) Ex. para usar o like findByNomeContains(String
 *          nome); findByNomeContainsIgnoreCase(String nome);
 * 
 */
public interface AlugavelTipoRepository extends JpaRepository<AlugavelTipo, Long>{

    /**
     * @apiNote buscarPorId(Long)
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id (Long)
     * @return AlugavelTipo
     */
    @Query("select t from AlugavelTipo t where t.id = ?1")
    public AlugavelTipo buscarPorId(Long id);

    /**
     * @apiNote findByDescr(String)
     *          Pesquisa por Descr (Exata)
     * @param descr (String)
     * @return AlugavelTipo
     */
    public AlugavelTipo findByDescr(String descr);

    /**
     * @apiNote findByDescrContainsIgnoreCase(String)
     *          Pesquisa por Descrição que contém
     * @param descr (String)
     * @return Set<AlugavelTipo>
     */
    public Set<AlugavelTipo> findByDescrContainsIgnoreCase(String descr);

    /**
     * @apiNote existsByDescr(String)
     *          Pesquisa se existe por descrição exata
     * @param descr (String)
     * @return boolean
     */
    public boolean existsByDescr(String descr);
    
}