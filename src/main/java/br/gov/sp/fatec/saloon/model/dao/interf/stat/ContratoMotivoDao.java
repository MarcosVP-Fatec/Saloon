package br.gov.sp.fatec.saloon.model.dao.interf.stat;

import java.util.List;

import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;

public interface ContratoMotivoDao {

    public ContratoMotivo salvarContratoMotivo(ContratoMotivo contratoMotivo);

    public ContratoMotivo cadastrarContratoMotivo(String descr);

    public ContratoMotivo buscarContratoMotivo(Long id);

    public List<ContratoMotivo> buscarContratoMotivo(String descr );

    public boolean removerContratoMotivo(Long id);

    public boolean removerContratoMotivo(ContratoMotivo contratoMotivo);
    
}