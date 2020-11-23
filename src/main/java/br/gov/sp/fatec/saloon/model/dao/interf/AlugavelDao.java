package br.gov.sp.fatec.saloon.model.dao.interf;

import java.math.BigDecimal;
import java.util.List;

import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

public interface AlugavelDao {
    
    public Alugavel salvar(Alugavel alugavel);

    public Alugavel cadastrar(String descr
                             ,Proprietario proprietario
                             ,AlugavelTipo alugavelTipo
                             ,String endereco
                             ,int capacidade
                             ,double valor);

    public Alugavel cadastrar(String descr
                             ,Proprietario proprietario
                             ,AlugavelTipo alugavelTipo
                             ,String endereco
                             ,int capacidade
                             ,BigDecimal valor);

    public Alugavel buscar(Long id);

    public List<Alugavel> buscar(String apelidoProprietario );

    public void consoleAlugavelPorProprietario(String apelidoProprietario);
    public void consoleAlugavelPorProprietario(Proprietario proprietario);

    public boolean remover(Long id);

    public boolean remover(Alugavel alugavel);

    public boolean existe(Long id);

}
