package br.gov.sp.fatec.saloon.model.repository.stat;

import java.util.List;

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
     * @apiNote Buscar por Id
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id
     * @return AlugavelTipo
     */
    @Query("select t from AlugavelTipo t where t.id = ?1")
    public AlugavelTipo buscarPorId(Long id);

    /**
     * @apiNote Pesquisa por Descr (Exato)
     * @param descr
     * @return AlugavelTipo
     */
    public AlugavelTipo findByDescr(String descr);

    /**
     * @apiNote Pesquisa por Descrição que contém
     * @param descr
     * @return List<AlugavelTipo>
     */
    public List<AlugavelTipo> findByDescrContainsIgnoreCase(String descr);

    /**
     * @apiNote Pesquisa se existe por descrição exata
     * @param descr
     * @return boolean
     */
    public boolean existsByDescr(String descr);
    
}




