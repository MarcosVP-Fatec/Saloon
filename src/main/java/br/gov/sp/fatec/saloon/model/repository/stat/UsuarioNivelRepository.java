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
     * @apiNote Buscar por Id
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id
     * @return UsuarioNivel
     */
    @Query("select u from UsuarioNivel u where u.id = ?1")
    public UsuarioNivel buscarPorId(Long id);

    /**
     * @apiNote Pesquisa por Descr (Exato)
     * @param descr
     * @return UsuarioNivel
     */
    public UsuarioNivel findByDescr(String descr);

    /**
     * @apiNote Pesquisa por Descrição que contém
     * @param descr
     * @return List<UsuarioNivel>
     */
    public List<UsuarioNivel> findByDescrContainsIgnoreCase(String descr);

    /**
     * @apiNote Pesquisa se existe por descrição exata
     * @param descr
     * @return boolean
     */
    public boolean existsByDescr(String descr);

}
