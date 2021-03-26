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
     * @apiNote Buscar por Id
     *          Esta opção traz sempre um objeto UsuarioNivel (Diferente do findById)
     * @param id
     * @return Usuario
     */
    @Query("select a from Alugavel a where a.id = ?1")
    public Alugavel buscarPorId(Long id);

    /**
     * @apiNote findByProprietarioUsuarioApelido(String) Pesquisa por apelido do usuáro (Exato)
     * @param apelido (String)
     * @return Alugavel
     */
    public Alugavel findByProprietarioApelido(String apelido);

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


