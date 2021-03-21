package br.gov.sp.fatec.saloon.model.repository.regi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

/**
 * @apiNote Dao da entidade Usuario
 * 
 *          I Spring fornece um Query Method Meu resultado pode voltar uma lista
 *          ou somente um O nome do método tem que começar com "findBy" +
 *          atributo Ex. para o atributo "nome" findByNome (Que seria o mesmo
 *          que findByNomeEquals) Ex. para usar o like findByNomeContains(String
 *          nome); findByNomeContainsIgnoreCase(String nome);
 * 
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    /**
     * @apiNote Buscar por Id
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id
     * @return Usuario
     */
    @Query("select u from Usuario u where u.id = ?1")
    public Usuario buscarPorId(Long id);

    /**
     * @apiNote findByDescr(String) Pesquisa por Descr (Exato)
     * @param descr (Strinc)
     * @return Usuario
     */
    public Usuario findByDescr(String descr);

    /**
     * @apiNote findByDescrContainsIgnoreCase(String) - Pesquisa por Descrição que contém
     * @param descr (String)
     * @return List<Usuario>
     */
    public List<Usuario> findByDescrContainsIgnoreCase(String descr);

    /**
     * @apiNote existsByDescr(String) - Pesquisa se existe por descrição exata
     * @param descr (String)
     * @return boolean
     */
    public boolean existsByDescr(String descr);



    
}
