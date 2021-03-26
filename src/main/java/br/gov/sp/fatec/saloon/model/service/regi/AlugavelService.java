package br.gov.sp.fatec.saloon.model.service.regi;

import java.math.BigDecimal;

import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;

public interface AlugavelService {

    /**
     * @apiNote persist
     *          Método que inclui ou altera um registro existente
     * @param id
     * @param descr
     * @param idProprietario
     * @param alugavelTipo
     * @param endereco
     * @param capacidade
     * @param valor
     * @return
     */
    public Alugavel persist(Long id, String descr, Long idProprietario, Long idAlugavelTipo,
                String endereco, int capacidade, BigDecimal valor);
    
    /**
     * @apiNote delete) --> Exclui um alugável passando o id
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(Long id);

}
