package br.gov.sp.fatec.saloon.model.repository.regi;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;

/**
 * @apiNote Dao da entidade Parâmetro
 */
public interface ParametroRepository extends JpaRepository<Parametro, Long>{

    public Optional<Parametro> findById(Long id);

    public Parametro findByCod(String cod);

    public boolean existsByCod(String cod);

    @Query("select p.numero from Parametro p where p.cod = ?1")
    public double parametroNumero(String cod);

    @Query("select p.str from Parametro p where p.cod = ?1")
    public String parametroTexto(String cod);

    @Query("select p.data from Parametro p where p.cod = ?1")
    public Date parametroData(String cod);

    @Query("select p.logico from Parametro p where p.cod = ?1")
    public boolean parametroLogico(String cod);

    public Set<Parametro> findByDescricaoContainsIgnoreCase(String descricao);
}