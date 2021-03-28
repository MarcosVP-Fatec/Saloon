package br.gov.sp.fatec.saloon.model.repository.regi;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

/**
 * @apiNote Dao da entidade Proprietário
 * 
 */
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long>{

    /**
     * @apiNote buscarPorId(Long)
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id (Long)
     * @return Proprietario
     */
    @Query("select p from Proprietario p where p.id = ?1")
    public Proprietario buscarPorId(Long id);

    /**
     * @apiNote findByApelido(String)
     *          Pesquisa por apelido (Exato)
     * @param apelido (String)
     * @return Proprietario
     */
    public Proprietario findByApelido(String apelido);

    /**
     * @apiNote findByNomeContainsIgnoreCase(String)
     *          Pesquisa por nome (parcial)
     * @param nome (String)
     * @return List<Usuario>
     */
    public Set<Proprietario> findByNomeContainsIgnoreCase(String nome);

    /**
     * @apiNote findByCpfContainsIgnoreCase(String)
     *          Pesquisa por nome (parcial)
     * @param cpf (String)
     * @return List<Proprietario>
     */
    public Set<Proprietario> findByCpfContainsIgnoreCase(String cpf);

    /**
     * @apiNote existsByApelido(String)
     *          Pesquisa se existe por apelido exata
     * @param apelido (String)
     * @return boolean
     */
    public boolean existsByApelido(String apelido);
    @Query("select count(p) > 0 from Proprietario p where p.apelido = ?1")
    public boolean existePorApelido(String apelido);

    /**
     * @apiNote findByEmail(String) - Pesquisa se existe por email exato
     * @param email (String)
     * @return boolean
     */
    public Proprietario findByEmailIgnoreCase(String email);


}