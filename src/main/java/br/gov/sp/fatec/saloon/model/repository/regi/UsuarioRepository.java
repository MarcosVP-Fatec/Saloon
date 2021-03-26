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
     * @apiNote findByApelido(String) Pesquisa por apelido (Exato)
     * @param apelido (String)
     * @return Usuario
     */
    public Usuario findByApelido(String apelido);

    /**
     * @apiNote findByNomeContainsIgnoreCase(String) - Pesquisa por nome (parcial)
     * @param nome (String)
     * @return List<Usuario>
     */
    public List<Usuario> findByNomeContainsIgnoreCase(String nome);

    /**
     * @apiNote findByCpfContainsIgnoreCase(String) - Pesquisa por nome (parcial)
     * @param cpf (String)
     * @return List<Usuario>
     */
    public List<Usuario> findByCpfContainsIgnoreCase(String cpf);

    /**
     * @apiNote existsByApelido(String) - Pesquisa se existe por apelido exata
     * @param apelido (String)
     * @return boolean
     */
    public boolean existsByApelido(String descr);
    
}
