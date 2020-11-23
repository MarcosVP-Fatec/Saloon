package br.gov.sp.fatec.saloon.model.dao.interf.laun;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;

public interface ContratoDao {

    public Contrato salvar(Contrato contrato);

    public Contrato cadastrar( Cliente        cliente
                             , Alugavel       alugavel
                             , Date           data      
                             , BigDecimal     reservaPaga
                             , ContratoMotivo contratoMotivo);

    public Contrato cadastrar( Cliente        cliente
                             , Alugavel       alugavel
                             , Date           data      
                             , BigDecimal     reservaPaga
                             , ContratoMotivo contratoMotivo
                             , String         festejoNomes
                             , int            festejoDia
                             , MesAno         festejoMes);

    public Contrato buscar(Long id);

    public List<Contrato> buscar(Cliente cliente);

    public List<Contrato> buscar(Alugavel alugavel, Cliente cliente);

    public boolean remover(Long id);

    public boolean remover(Contrato contrato);

}