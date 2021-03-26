package br.gov.sp.fatec.saloon.model.service.laun;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.repository.laun.ContratoRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.AlugavelRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.ContratoMotivoRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.MesAnoRepository;

@Service("ContratoService")
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository          contratoRepo;

    @Autowired
    private ClienteRepository           clienteRepo;

    @Autowired
    private AlugavelRepository          alugavelRepo;

    @Autowired
    private MesAnoRepository            mesAnoRepo;

    @Autowired
    private ContratoMotivoRepository    contratoMotivoRepo;

    @Override
    @Transactional
    public Contrato persist( Long       id
                           , Long       idCliente
                           , Long       idAlugavel
                           , Date       data
                           , BigDecimal reservaPaga
                           , Long       idMotivo
                           , String     festejoNomes
                           , int        festejoDia
                           , Long       idMes) {

        Contrato contrato;

        if (id != null) {
            if (!contratoRepo.existsById(id) ){
                return null;
            }
            contrato = contratoRepo.buscarPorId(id);
        } else {
            contrato = new Contrato();
        }

        contrato.setCliente(clienteRepo.buscarPorId(idCliente));
        contrato.setAlugavel(alugavelRepo.buscarPorId(idAlugavel));
        contrato.setData(data);
        contrato.setReservaPaga(reservaPaga);
        contrato.setContratoMotivo(contratoMotivoRepo.buscarPorId(idMotivo));
        contrato.setFestejoNomes(festejoNomes);
        contrato.setFestejoMes(mesAnoRepo.buscarPorId(idMes));
        contrato.setfestejoDia(festejoDia);

        return contratoRepo.save(contrato);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!contratoRepo.existsById(id)){
            return true;
        }
        contratoRepo.deleteById(id);
        return !contratoRepo.existsById(id);
    }
   
}
