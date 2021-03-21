package br.gov.sp.fatec.saloon.model.service.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;

public interface ContratoMotivoService {

    /**
     * @apiNote inc() --> Inclui um Motivo de Contrato
     * @param descr
     * @return ContratoMotivo
     */
    public ContratoMotivo inc(String descr);

    /**
     * @apiNote alt() --> Altera um Motivo e Contrato
     * @param id (Long) - PK
     * @param descr (String)
     * @return ContratoMotivo
     */
    public ContratoMotivo alt(Long id, String descr);

    /**
     * @apiNote del() --> Exclui um Motivo de Contrato 
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(Long id);

    /**
     * @apiNote del() --> Exclui um Motivo de Contrato 
     * @param descr (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(String descr);
    
}
