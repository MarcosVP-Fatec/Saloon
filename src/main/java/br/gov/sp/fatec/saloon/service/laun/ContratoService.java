package br.gov.sp.fatec.saloon.service.laun;

import java.math.BigDecimal;
import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

public interface ContratoService {

    public Contrato persist( Long       id
                           , Long       idCliente
                           , Long       idAlugavel
                           , Date       data
                           , BigDecimal reservaPaga
                           , Long       idMotivo
                           , String     festejoNomes
                           , int        festejoDia
                           , Long       idMes );

    public Contrato persist( Contrato contrato
                           , Cliente  cliente
                           , Alugavel alugavel);

    public boolean delete(Long id);

    public Contrato novoClienteContrato( String         cliCpf
                                       , String         cliNome
                                       , String         cliTelDdd
                                       , String         cliTelNumero
                                       , Date           cttData
                                       , BigDecimal     cttReservaPaga
                                       , String         cttFestejoNomes
                                       , int            cttFestejoDia
                                       , Long           cttFestejoMes
                                       , Long           cttContratoMotivo
                                       , Proprietario   proprietario 
                                       , Alugavel       alugavel);
                                       


}

