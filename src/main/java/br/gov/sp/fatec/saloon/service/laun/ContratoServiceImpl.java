package br.gov.sp.fatec.saloon.service.laun;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.repository.laun.ContratoRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.AlugavelRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.ContratoMotivoRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.MesAnoRepository;
import br.gov.sp.fatec.saloon.service.regi.ClienteService;

@Service("ContratoService")
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository contratoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ClienteService  clienteService;

    @Autowired
    private AlugavelRepository alugavelRepo;

    @Autowired
    private MesAnoRepository mesAnoRepo;

    @Autowired
    private ProprietarioRepository proprietarioRepo;

    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

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
            if (!contratoRepo.existsById(id)) {
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
        if (!contratoRepo.existsById(id)) {
            return true;
        }
        contratoRepo.deleteById(id);
        return !contratoRepo.existsById(id);
    }

    @Override
    @Transactional
    public Contrato persist(Contrato contrato, Cliente cliente, Alugavel alugavel) {

        if (clienteRepo.existsByNome(cliente.getNome())) {
            cliente = clienteRepo.findByNome(cliente.getNome());
        } else {
            cliente.setId(null);
            clienteRepo.save(cliente);
        }
        
        if (proprietarioRepo.existsByApelido(alugavel.getProprietario().getApelido())){
            alugavel.setProprietario( proprietarioRepo.findByApelido(alugavel.getProprietario().getApelido()));
        } else {
            alugavel.getProprietario().setId(null);
            proprietarioRepo.save(alugavel.getProprietario());
        }

        if (alugavelRepo.existsByDescr(alugavel.getDescr())){
            alugavel = alugavelRepo.findByDescr(alugavel.getDescr());
        } else {
            alugavel.setId(null);
            alugavelRepo.save(alugavel);
        }

        contrato.setCliente(cliente);
        contrato.setAlugavel(alugavel);
        contratoRepo.save(contrato);

        return contrato;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Contrato novoClienteContrato(String     cliCpf
                                       ,String     cliNome
                                       ,String     cliTelDdd
                                       ,String     cliTelNumero){
                                // ,String     pro_apelido
                                // ,String     pro_email
                                // ,String     pro_senha
                                // ,Date       pro_nascimento
                                // ,String     pro_cpf
                                // ,String     alu_descr
                                // ,String     alu_endereco
                                // ,int        alu_capacidade
                                // ,BigDecimal alu_valor
                                // ,Date       ctt_data
                                // ,BigDecimal ctt_reservaPaga ){

        //Cliente cliente = new Cliente(null, cliCpf, cliNome, cliTelDdd, cliTelNumero);
        Cliente cliente = clienteService.persist(cliCpf, cliNome, cliTelDdd, cliTelNumero);

        Contrato contrato = new Contrato();
        
        return contrato;
    }   


}
