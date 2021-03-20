package br.gov.sp.fatec.saloon.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;

/**
 * @apiNote Dao da entidade MesAno
 * 
 *          I Spring fornece um Query Method Meu resultado pode voltar uma lista
 *          ou somente um O nome do método tem que começar com "findBy" +
 *          atributo Ex. para o atributo "nome" findByNome (Que seria o mesmo
 *          que findByNomeEquals) Ex. para usar o like findByNomeContains(String
 *          nome); findByNomeContainsIgnoreCase(String nome);
 * 
 */
public interface MesAnoRepository  extends JpaRepository<MesAno, Long>{

    /**
     * @apiNote Pesquisa por Id
     * @param id
     * @return MesAno
     */
    @Query("select m from MesAno m where m.id = ?1")
     public MesAno buscarPorId(Long id);

    /**
     * @apiNote Buscar por numero;
     * @param String numero
     * @return MesAno
     */
    @Query("select m from MesAno m where m.numero = ?1")
    public MesAno buscarPorNumero(String numero);
    
    /**
     * @apiNote Lista de meses
     * @return List<MesAno>
     */
    @Query("select m from MesAno m where m.numero = ?1 order by m.numero")
    public List<MesAno> listaDeMeses();

}