package br.gov.sp.fatec.saloon.model.service.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

public interface AlugavelTipoService {

    /**
     * @apiNote persist --> Inclui/Altera um Tipo de Alugável
     * @param descr
     * @return AlugavelTipo
     */
    public AlugavelTipo persist(Long id, String descr);

    /**
     * @apiNote delete --> Exclui um Tipo de Alugável
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