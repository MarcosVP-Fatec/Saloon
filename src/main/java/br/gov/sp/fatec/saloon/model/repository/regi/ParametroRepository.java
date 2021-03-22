package br.gov.sp.fatec.saloon.model.repository.regi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;

/**
 * @apiNote Dao da entidade Parâmetro
 */
public interface ParametroRepository extends JpaRepository<Parametro, Long>{

    /**
     * @apiNote Buscar por Id
     *          Esta opção traz sempre um objeto Parametro (Diferente do findById)
     * @param id
     * @return Parametro
     */
    @Query("select p from Parametro p where p.id = ?1")
    public Parametro buscarPorId(Long id);

    /**
     * @apiNote findByCod(String) Buscar por Cod
     * @param Cod (String)
     * @return Parametro
     */
    public Parametro findByCod(String cod);

    /**
     * @apiNote existsByCod(String) Verifica se existe por cod
     * @param Cod (String)
     * @return boolean
     */
    public boolean existsByCod(String cod);

    /**
     * @apiNote parametroNumero(String) Buscar por Cod
     * @param Cod (String)
     * @return double
     */
    @Query("select p.numero from Parametro p where p.cod = ?1")
    public double parametroNumero(String cod);

    /**
     * @apiNote parametroTexto(String) Buscar por Cod
     * @param Cod (String)
     * @return String
     */
    @Query("select p.str from Parametro p where p.cod = ?1")
    public String parametroTexto(String cod);

    /**
     * @apiNote parametroData(String) Buscar por Cod
     * @param Cod (String)
     * @return Date
     */
    @Query("select p.data from Parametro p where p.cod = ?1")
    public String parametroData(String cod);

    /**
     * @apiNote parametroLogico(String) Buscar por Cod
     * @param Cod (String)
     * @return boolean
     */
     @Query("select p.logico from Parametro p where p.cod = ?1")
     public boolean parametroLogico(String cod);
}