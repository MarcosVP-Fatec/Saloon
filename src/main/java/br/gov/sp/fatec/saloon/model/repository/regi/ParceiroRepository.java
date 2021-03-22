package br.gov.sp.fatec.saloon.model.repository.regi;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

/**
 * @apiNote Dao da entidade Proprietário
 * @version 1.1 (Spring-Boot)
 */
public interface ParceiroRepository extends JpaRepository<Parceiro, Long>{

    /**
     * @apiNote buscarPorId() --> Busca por Id
     *          Esta opção traz sempre um objeto Parceiro (Diferente do findById)
     * @param id (Long)
     * @return Parceiro
     */
    @Query("select p from Parceiro p where p.id = ?1")
    public Parceiro buscarPorId(Long id);

    /**
     * @apiNote findByApelido(String) Pesquisa por apelido (Exato)
     * @param apelido (String)
     * @return Parceiro
     */
    public Parceiro findByApelido(String apelido);

    /**
     * @apiNote findByNomeContainsIgnoreCase(String) - Pesquisa por nome (parcial)
     * @param nome (String)
     * @return List<Parceiro>
     */
    public Set<Parceiro> findByNomeContainsIgnoreCase(String nome);

    /**
     * @apiNote findByCpfContainsIgnoreCase(String) - Pesquisa por nome (parcial)
     * @param cpf (String)
     * @return List<Usuario>
     */
    public Set<Parceiro> findByCpfContainsIgnoreCase(String cpf);

    /**
     * @apiNote existsByApelido(String) - Pesquisa se existe por apelido exata
     * @param apelido (String)
     * @return boolean
     */
    public boolean existsByApelido(String descr);
    
    /**
     * @apiNote findByEmail(String) - Pesquisa se existe por email exato
     * @param email (String)
     * @return boolean
     */
    public Parceiro findByEmailIgnoreCase(String email);

}



