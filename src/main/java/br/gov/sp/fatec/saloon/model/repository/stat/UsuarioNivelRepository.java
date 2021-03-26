package br.gov.sp.fatec.saloon.model.repository.stat;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;

/**
 * @apiNote Dao da entidade NivelUsuario
 * 
 *          I Spring fornece um Query Method Meu resultado pode voltar uma lista
 *          ou somente um O nome do método tem que começar com "findBy" +
 *          atributo Ex. para o atributo "nome" findByNome (Que seria o mesmo
 *          que findByNomeEquals) Ex. para usar o like findByNomeContains(String
 *          nome); findByNomeContainsIgnoreCase(String nome);
 * 
 */
public interface UsuarioNivelRepository extends JpaRepository<UsuarioNivel, Long> {

    /**
     * @apiNote Pesquisa por Id
     * @param id
     * @return UsuarioNivel
     */
    public Optional<UsuarioNivel> findById(Long id);
    
    /**
     * @apiNote buscarPorId(Long)
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id (Long)
     * @return UsuarioNivel
     */
    @Query("select u from UsuarioNivel u where u.id = ?1")
    public UsuarioNivel buscarPorId(Long id);

    /**
     * @apiNote findByDescr(String)
     *          Pesquisa por Descr (Exata)
     * @param descr (String)
     * @return UsuarioNivel
     */
    public UsuarioNivel findByDescr(String descr);

    /**
     * @apiNote findByDescrContainsIgnoreCase(String)
     *          Pesquisa por Descrição que contém
     * @param descr (String)
     * @return List<UsuarioNivel>
     */
    public List<UsuarioNivel> findByDescrContainsIgnoreCase(String descr);

    /**
     * @apiNote existsByDescr(String)
     *          Pesquisa se existe por descrição exata
     * @param descr (String)
     * @return boolean
     */
    public boolean existsByDescr(String descr);

}
