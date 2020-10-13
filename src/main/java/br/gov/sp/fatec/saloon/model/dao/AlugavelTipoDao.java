package br.gov.sp.fatec.saloon.model.dao;

import br.gov.sp.fatec.saloon.model.entity.AlugavelTipo;

public interface AlugavelTipoDao {

    public AlugavelTipo salvarAlugavelTipo(AlugavelTipo alugavelTipo);

    public AlugavelTipo cadastrarAlugavelTipo(String descr );

    public AlugavelTipo buscarAlugavelTipo(Long id);

    public AlugavelTipo buscarAlugavelTipo(String descr );

    public boolean removerAlugavelTipo(Long id);

    public boolean removerAlugavelTipo(AlugavelTipo alugavelTipo);
    
}