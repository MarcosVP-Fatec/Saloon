package br.gov.sp.fatec.saloon.model.service.laun;

import java.math.BigDecimal;
import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;

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

    public boolean delete(Long id);

}

