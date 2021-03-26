package br.gov.sp.fatec.saloon.model.repository.laun;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;

/**
 * @apiNote Dao da entidade Contrato
 * 
 */
public interface ContratoRepository extends JpaRepository<Contrato, Long>{

    @Query("select c from Contrato c where c.id = ?1")
    public Contrato buscarPorId(Long id);

    @Query("select c from Contrato c inner join c.cliente d where d.cpf_cnpj = ?1")
    public Set<Contrato> buscarPorCpfCliente(String cpf);

    public Set<Contrato> findByClienteNome(String nomeCliente);

    public Set<Contrato> findByClienteNomeContainsIgnoreCase(String nomeCliente);

    @Query("select c from Contrato      c " +
           "      inner join c.cliente  d " +
           "      inner join c.alugavel a " +
           " where d.id = ?2 and a.id = ?1" +
           " order by c.data              ")
    public Set<Contrato> listaPorAlugavelCliente( Long idAlugavel,  Long idCliente);

    @Query("select count(c) from Contrato c")
    public Long countAll();

    public Set<Contrato> findByFestejoNomesContainsIgnoreCase(String nomes);

}


