package br.gov.sp.fatec.saloon.service.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

public interface AlugavelTipoService {

    /**
     * @apiNote persist(Long, String) --> Inclui/Altera um Tipo de Alugável
     * @param id (Long)
     * @param descr (String)
     * @return AlugavelTipo
     */
    public AlugavelTipo persist(Long id, String descr);
    public AlugavelTipo persist(         String descr);

    /**
     * @apiNote delete(Long) --> Exclui um Tipo de Alugável
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(Long id);

    /**
     * @apiNote delete --> Exclui um Tipo de Alugavel
     * @param descr (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(String descr);
   
}