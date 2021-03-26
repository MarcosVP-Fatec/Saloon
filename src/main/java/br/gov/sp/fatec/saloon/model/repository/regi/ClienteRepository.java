package br.gov.sp.fatec.saloon.model.repository.regi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;

/**
 * @apiNote Dao da entidade Cliente
 * 
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    @Query("select c from Cliente c where c.id = ?1")
    public Cliente buscarPorId(Long id);

    public Cliente findByNome(String nome);
    
    public Cliente findByParceiroCpf(String cpf);

    public Cliente findByParceiroApelido(String apelido);

    public boolean existsByNome(String nome);

    //public Set<Cliente> findByNomeContainsIgnoreCase(String nome);

}

    /*
    @Query("select a from Alugavel a inner join a.proprietario p where p.apelido = ?1")
    public Set<Alugavel> listaPorProprietarioApelido(String apelido);
    @Query("select a from Alugavel a inner join a.proprietario p where p.id = ?1")
    public Set<Alugavel> listaPorProprietarioId(Long idProprietario);
*/
