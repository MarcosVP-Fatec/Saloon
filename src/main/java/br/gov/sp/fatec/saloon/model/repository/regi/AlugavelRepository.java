package br.gov.sp.fatec.saloon.model.repository.regi;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;

/**
 * @apiNote Dao da entidade Alugavel
 * 
 */
public interface AlugavelRepository extends JpaRepository<Alugavel, Long>{
    
    /**
     * @apiNote buscarPorId(Long)
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id (Long)
     * @return Usuario
     */
    @Query("select a from Alugavel a where a.id = ?1")
    public Alugavel buscarPorId(Long id);

    /**
     * @apiNote findByDescr(String)
     *          Pesquisa por descrição do alugavel
     * @param descr (String)
     * @return Usuario
     */
    public Alugavel findByDescr(String descr);

    /**
     * @apiNote existsByDescr(String)
     *          Pesquisa por descrição do alugavel
     * @param descr (String)
     * @return Usuario
     */
    public boolean existsByDescr(String descr);

    /**
     * @apiNote findByProprietarioUsuarioApelido(String) Pesquisa por apelido do usuáro (Exato)
     * @param apelido (String)
     * @return Alugavel
     */
    public Alugavel findByProprietarioApelido(String apelido);
    @Query("select a from Alugavel a inner join a.proprietario p where p.apelido = ?1")
    public Set<Alugavel> listaPorProprietarioApelido(String apelido);
    @Query("select a from Alugavel a inner join a.proprietario p where p.id = ?1")
    public Set<Alugavel> listaPorProprietarioId(Long idProprietario);

    /**
     * @apiNote findByProprietarioUsuarioNomeContainsIgnoreCase(String) - Pesquisa por nome do proprietário (parcial)
     * @param nome (String)
     * @return Set<Alugavel>
     */
    public Set<Alugavel> findByProprietarioNomeContainsIgnoreCase(String nome);

    /**
     * @apiNote findByProprietarioUsuarioCpf(String) - Pesquisa por cpf
     * @param cpf (String)
     * @return Set<Alugavel>
     */
    public Set<Alugavel> findByProprietarioCpf(String cpf);

    /**
     * @apiNote existsByApelido(String) - Pesquisa se existe por apelido exata
     * @param apelido (String)
     * @return boolean
     */
    public boolean existsByProprietarioApelido(String descr);

}

