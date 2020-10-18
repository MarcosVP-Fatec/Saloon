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

    public Contrato salvarContrato(Contrato contrato);

    public Contrato cadastrarContrato( Cliente        cliente
                                     , Alugavel       alugavel
                                     , Date           data      
                                     , BigDecimal     reservaPaga
                                     , ContratoMotivo contratoMotivo);

                                     public Contrato cadastrarContrato( Cliente        cliente
                                     , Alugavel       alugavel
                                     , Date           data      
                                     , BigDecimal     reservaPaga
                                     , ContratoMotivo contratoMotivo
                                     , String         festejoNomes
                                     , int            festejoDia
                                     , MesAno         festejoMes);

    public Contrato buscarContrato(Long id);

    public List<Contrato> buscarContratosDoCliente(Cliente cliente);

    public List<Contrato> buscarContrato(Alugavel alugavel, Cliente cliente);

    public boolean removerContrato(Long id);

    public boolean removerContrato(Contrato contrato);

}