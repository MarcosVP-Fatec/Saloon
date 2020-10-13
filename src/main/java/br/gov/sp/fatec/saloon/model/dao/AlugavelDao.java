package br.gov.sp.fatec.saloon.model.dao;

import java.math.BigDecimal;
import java.util.List;

import br.gov.sp.fatec.saloon.model.entity.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.entity.Proprietario;

public interface AlugavelDao {
    
    public Alugavel salvarAlugavel(Alugavel alugavel);

    public Alugavel cadastrarAlugavel(String descr
                                     ,Proprietario proprietario
                                     ,AlugavelTipo alugavelTipo
                                     ,String endereco
                                     ,int capacidade
                                     ,double valor);

    public Alugavel cadastrarAlugavel(String descr
                                     ,Proprietario proprietario
                                     ,AlugavelTipo alugavelTipo
                                     ,String endereco
                                     ,int capacidade
                                     ,BigDecimal valor);

    public Alugavel buscarAlugavel(Long id);

    public List<Alugavel> buscarAlugavel(String apelidoProprietario );

    public void consoleAlugavelPorProprietario(String apelidoProprietario);
    public void consoleAlugavelPorProprietario(Proprietario proprietario);


    public boolean removerAlugavel(Long id);

    public boolean removerAlugavel(Alugavel alugavel);

}
