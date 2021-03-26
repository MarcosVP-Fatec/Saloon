package br.gov.sp.fatec.saloon.model.service.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;

public interface ContratoMotivoService {

    /**
     * @apiNote persist(Long, String) --> Inclui/Altera um Motivo de Contrato
     * @param id    (Long)
     * @param descr (String)
     * @return ContratoMotivo
     */
    public ContratoMotivo persist(Long id, String descr);
    public ContratoMotivo persist(         String descr);

    /**
     * @apiNote delete(Long)
     *          Exclui um Motivo de Contrato 
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(Long id);

    /**
     * @apiNote delete(String)
     *          Exclui um Motivo de Contrato 
     * @param descr (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(String descr);
    
}
