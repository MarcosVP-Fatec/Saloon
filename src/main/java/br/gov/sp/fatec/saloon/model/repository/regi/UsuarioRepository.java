package br.gov.sp.fatec.saloon.model.repository.regi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("select u from Usuario u where u.usuarioNivel = 1")
    public List<Usuario> buscarUsuariosProprietarios();

    @Query("select u from Usuario u where u.id = ?1")
    public Usuario buscarPorId(Long id);

    public Optional<Usuario> findById(Long id);

    public Usuario findByApelido(String apelido);

    public List<Usuario> findByNomeContainsIgnoreCase(String nome);

    public List<Usuario> findByCpfContainsIgnoreCase(String cpf);

    public boolean existsByApelido(String descr);

}
