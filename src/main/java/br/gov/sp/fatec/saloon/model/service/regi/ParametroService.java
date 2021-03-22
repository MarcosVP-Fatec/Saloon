package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;

public interface ParametroService {

    /**
     * @apiNote inc() --> Inclui um parâmetro número passando os seus campos
     * @param cod (String)
     * @param descricao (String)
     * @param numero (double)
     * @return Parametro
     */
    public Parametro inc(String cod, String descricao, double numero);

    /**
     * @apiNote inc() --> Inclui um parâmetro texto passando os seus campos
     * @param cod (String)
     * @param descricao (String)
     * @param texto (String)
     * @return Parametro
     */
    public Parametro inc(String cod, String descricao, String texto);

    /**
     * @apiNote inc() --> Inclui um parâmetro data passando os seus campos
     * @param cod (String)
     * @param descricao (String)
     * @param data (Date)
     * @return Parametro
     */
    public Parametro inc(String cod, String descricao, Date data);

    /**
     * @apiNote inc() --> Inclui um parâmetro lógico passando os seus campos
     * @param cod (String)
     * @param descricao (String)
     * @param logico (boolean)
     * @return Parametro
     */
    public Parametro inc(String cod, String descricao, boolean logico);

    /**
     * @apiNote alt() --> Altera um parâmetro número passando os seus campos
     * @param id (Long)
     * @param descricao (String)
     * @param numero (double)
     * @return Parametro
     */
    public Parametro alt(Long id, String descricao, double numero);

    /**
     * @apiNote alt() --> Altera um parâmetro texto passando os seus campos
     * @param id (Long)
     * @param descricao (String)
     * @param texto (String)
     * @return Parametro
     */
    public Parametro alt(Long id, String descricao, String texto);

    /**
     * @apiNote alt() --> Altera um parâmetro data passando os seus campos
     * @param id (Long)
     * @param descricao (String)
     * @param data (Date)
     * @return Parametro
     */
    public Parametro alt(Long id, String descricao, Date data);

    /**
     * @apiNote alt() --> Altera um parâmetro lógico passando os seus campos
     * @param id (Long)
     * @param descricao (String)
     * @param logico (boolean)
     * @return Parametro
     */
    public Parametro alt(Long id, String descricao, boolean logico);

    /**
     * @apiNote del() --> Exclui um parâmetro passando o id
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(Long id);

    /**
     * @apiNote del() --> Exclui um parâmetro passando o cod
     * @param cod (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(String cod);
    
}

    

