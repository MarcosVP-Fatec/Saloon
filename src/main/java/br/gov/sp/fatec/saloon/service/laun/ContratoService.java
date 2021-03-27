package br.gov.sp.fatec.saloon.service.laun;

import java.math.BigDecimal;
import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;

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

}

