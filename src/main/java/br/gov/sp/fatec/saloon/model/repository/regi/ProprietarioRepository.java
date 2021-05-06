package br.gov.sp.fatec.saloon.model.repository.regi;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.saloon.controller.View;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

/**
 * @apiNote Dao da entidade Proprietário
 * 
 */
@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long>{

    @Query("select p from Proprietario p")
    public List<Proprietario> buscarProprietarios();
    
    @Query("select p from Proprietario p where p.id = ?1")
    public Proprietario buscarPorId(Long id);

    public Proprietario findByApelido(String apelido);

    public Set<Proprietario> findByNomeContainsIgnoreCase(String nome);

    public Set<Proprietario> findByCpfContainsIgnoreCase(String cpf);

    public boolean existsByApelido(String apelido);
    public boolean existsByCpf(String cpf);

    @Query("select count(p) > 0 from Proprietario p where p.apelido = ?1")
    public boolean existePorApelido(String apelido);

    public Proprietario findByEmailIgnoreCase(String email);

}