package br.gov.sp.fatec.saloon.model.service.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

public interface AlugavelTipoService {

    /**
     * @apiNote inc() --> Inclui um Tipo de Alugável
     * @param descr
     * @return AlugavelTipo
     */
    public AlugavelTipo inc(String descr);

    /**
     * @apiNote alt() --> Altera um Tipo de Alugável
     * @param id (Long) - PK
     * @param descr (String)
     * @return AlugavelTipo
     */
    public AlugavelTipo alt(Long id, String descr);

    /**
     * @apiNote del() --> Exclui um Tipo de Alugável
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(Long id);

    /**
     * @apiNote del() --> Exclui um Tipo de Alugavel
     * @param descr (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(String descr);
    
}


