package br.gov.sp.fatec.saloon.model.dao.interf;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

public interface AlugavelTipoDao {

    public AlugavelTipo salvarAlugavelTipo(AlugavelTipo alugavelTipo);

    public AlugavelTipo cadastrarAlugavelTipo(String descr );

    public AlugavelTipo buscarAlugavelTipo(Long id);

    public AlugavelTipo buscarAlugavelTipo(String descr );

    public boolean removerAlugavelTipo(Long id);

    public boolean removerAlugavelTipo(AlugavelTipo alugavelTipo);
    
}