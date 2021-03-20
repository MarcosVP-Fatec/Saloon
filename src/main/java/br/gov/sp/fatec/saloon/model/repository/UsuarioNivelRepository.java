package br.gov.sp.fatec.saloon.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;

/**
 * @apiNote Dao da entidade NivelUsuario
 * 
 *          I Spring fornece um Query Method Meu resultado pode voltar uma lista
 *          ou somente um O nome do método tem que começar com "findBy" +
 *          atributo Ex. para o atributo "nome" findByNome (Que seria o mesmo
 *          que findByNomeEquals) Ex. para usar o like findByNomeContains(String
 *          nome); findByNomeContainsIgnoreCase(String nome);
 * 
 */
public interface UsuarioNivelRepository extends JpaRepository<UsuarioNivel, Long> {

    /**
     * @apiNote Pesquisa por Id
     * @param id
     * @return UsuarioNivel
     */
    //public Optional<UsuarioNivel> findById(Long id);

    /**
     * @apiNote Pesquisa por Descrição que contém
     * @param descr
     * @return
     */
    public List<UsuarioNivel> findByDescrContainsIgnoreCase(String descr);

}
