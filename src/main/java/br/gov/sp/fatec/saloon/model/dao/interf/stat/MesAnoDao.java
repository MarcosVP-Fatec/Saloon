package br.gov.sp.fatec.saloon.model.dao.interf.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;

public interface MesAnoDao {

    public MesAno salvarMesAno(MesAno mesAno);

    public MesAno cadastrarMesAno(String id, String descr);

    public MesAno buscarMesAno(Long id);
    
    public MesAno buscarMesAno(String mes);
    
}