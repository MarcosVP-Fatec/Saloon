package br.gov.sp.fatec.saloon.model.dao.interf.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;

public interface MesAnoDao {

    public MesAno salvar(MesAno mesAno);

    public MesAno cadastrar(String id, String descr);

    public MesAno buscar(Long id);
    
    public MesAno buscar(String mes);
    
}